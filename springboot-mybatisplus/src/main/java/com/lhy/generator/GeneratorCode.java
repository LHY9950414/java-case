package com.lhy.generator;

import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.lhy.config.exception.MybatisPlusException;
import com.lhy.enums.StatusCodeEnum;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author liheyan
 */
@Slf4j
public class GeneratorCode {


    /**
     * 数据库连接、驱动、账号、密码
     */
    private static final String MYSQL_URL = "jdbc:mysql://127.0.0.1:3306/ers2.0?useUnicode=true&characterEncoding=UTF-8&useSSL=false&connectTimeout=60000&socketTimeout=60000&autoReconnect=true&autoReconnectForPools=true&failOverReadOnly=false&serverTimezone=GMT%2B8";
    private static final String DRIVER_NAME = "com.mysql.jdbc.Driver";
    private static final String USER_NAME = "root";
    private static final String PASS_WORD = "root";
    /**
     * 获取当前服务 路径、子PATH、mapper的xml文件位置、xml引用模版配置、mapper.xml命名、作者
     */
    private static final String USER_DIR = "user.dir";
    private static final String PROJECT_PATH = "/springboot-mybatisplus/src/main/java/";
    private static final String TEMPLATES_RESOURCES_MAPPER_XML = "/springboot-mybatisplus/src/main/resources/mapper/";
    private static final String TEMPLATES_MAPPER_XML = "/templates/mapper.xml.ftl";

    private static final String MAPPER_PATH = "Mapper";
    private static final String AUTHOR = "liheyan";
    /**
     * PARENT 包路径
     * ENTITY dao路径
     * MAPPER mapper路径
     * SERVICE service路径
     * SERVICE_IMPL service.impl路径
     * CONTROLLER controller路径
     */
    private static final String PARENT = "com.lhy";
    private static final String ENTITY = "pojo.po";
    private static final String MAPPER = "pojo.dao";
    private static final String SERVICE = "Service";
    private static final String SERVICE_IMPL = "service.impl";
    private static final String CONTROLLER = "controller";

    public static void main(String[] args) {
        // 代码生成器
        AutoGenerator autoGenerator = new AutoGenerator();
        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        String projectPath = System.getProperty(USER_DIR);
        gc.setOutputDir(projectPath + PROJECT_PATH);
        //作者
        gc.setAuthor(AUTHOR);
        //打开输出目录
        gc.setOpen(false);
        //xml开启 BaseResultMap
        gc.setBaseResultMap(true);
        //xml 开启BaseColumnList
        gc.setBaseColumnList(true);
        // 覆盖文件 false（否），true（是）
        gc.setFileOverride(false);
        // 实体属性 Swagger2 注解
        gc.setSwagger2(true);
        // 时间类型对应策略: 只使用 java.util.date 代替
        gc.setDateType(DateType.ONLY_DATE);
        autoGenerator.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        dataSourceConfig.setUrl(MYSQL_URL);
        dataSourceConfig.setDriverName(DRIVER_NAME);
        dataSourceConfig.setUsername(USER_NAME);
        dataSourceConfig.setPassword(PASS_WORD);
        autoGenerator.setDataSource(dataSourceConfig);

        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setParent(PARENT)
                .setEntity(ENTITY)
                .setMapper(MAPPER)
                .setService(SERVICE)
                .setServiceImpl(SERVICE_IMPL)
                .setController(CONTROLLER);
        autoGenerator.setPackageInfo(pc);

        // 自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                // todo nothing
            }
        };
        // 自定义输出配置
        List<FileOutConfig> focList = new ArrayList<>();
        // 自定义配置会被优先输出
        focList.add(new FileOutConfig(TEMPLATES_MAPPER_XML) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                return projectPath + TEMPLATES_RESOURCES_MAPPER_XML + tableInfo.getEntityName() + MAPPER_PATH + StringPool.DOT_XML;
            }
        });
        cfg.setFileOutConfigList(focList);
        autoGenerator.setCfg(cfg);

        // 配置模板
        TemplateConfig templateConfig = new TemplateConfig();
        templateConfig.setXml(null);
        autoGenerator.setTemplate(templateConfig);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        //数据库表映射到实体的命名策略
        strategy.setNaming(NamingStrategy.underline_to_camel);
        //数据库表字段映射到实体的命名策略
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        //lombok模型
        strategy.setEntityLombokModel(true);
        //生成 @RestController 控制器
        strategy.setRestControllerStyle(true);
        ;
        strategy.setInclude(scanner("表名, 多个英文逗号分割, 不允许存在空格").split(","));
        strategy.setControllerMappingHyphenStyle(true);
        autoGenerator.setStrategy(strategy);
        autoGenerator.setTemplateEngine(new FreemarkerTemplateEngine());
        autoGenerator.execute();
    }


    /**
     * 读取控制台内容
     * @param tip
     * @return
     */
    public static String scanner(String tip) {
        Scanner scanner = new Scanner(System.in);
        StringBuilder help = new StringBuilder();
        help.append("请输入" + tip + ": ");
        log.info(help.toString());
        if (scanner.hasNext()) {
            String ipt = scanner.next();
            if (StringUtils.isNotEmpty(ipt)) {
                return ipt;
            }
        }
        throw new MybatisPlusException(StatusCodeEnum.ERROR.getCode(), "请输入正确的" + tip);
    }
}
