package com.test.mybatisplus;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.baomidou.mybatisplus.generator.fill.Column;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class MybatisPlusGenerator {

    public static void main(String[] args) {
        String dbUrl = "jdbc:mysql://47.106.74.177:13306/test_mybatis_plus?allowPublicKeyRetrieval=true&useSSL=false";
        String dbUser = "hd";
        String dbPassword = "hd123456";
        String author = "hd";
        String parentPackageName = "com.test.mybatisplus";
        List<String> tables = Arrays.asList("t_product");
        List<String> tablePrefix = Arrays.asList("SYS_", "XXL_");

        String PROJECT_ROOT_PATH =  "D://";
        String BASE_PATH = PROJECT_ROOT_PATH + "/src/main/java/com/test/mybatisplus";
        // 六个文件的路径
        String entityPath = BASE_PATH + "/po";
        String mapperPath = BASE_PATH + "/dao";
        String mapperXmlPath = PROJECT_ROOT_PATH + "/src/main/resources/mapper";
        String servicePath = BASE_PATH + "/service";
        String serviceImplPath = BASE_PATH + "/service/impl";
        String controllerPath = BASE_PATH + "/controller";

        FastAutoGenerator fastAutoGenerator = FastAutoGenerator.create(dbUrl, dbUser, dbPassword);
        fastAutoGenerator.globalConfig(builder -> builder
                        // 作者名称
                        .author(author)
                        // 禁止打开输出目录
                        .disableOpenDir()
                        // 指定输出目录
                        // .outputDir(packagePath)
                        // 开启swagger2。注释掉则默认关闭。
                        .enableSwagger()
                        // 时间策略
                        .dateType(DateType.TIME_PACK)
                        // 时间格式
                        .commentDate("yyyy-MM-dd")
                )
                .templateConfig((scanner, builder) -> builder
                        // 设置空，则不会生成Controller类
                        .controller("")
                        .service("templates/service.java")
                )
                .packageConfig((scanner, builder) -> builder
                        // 阶段1：各个文件的包名设置，用来拼接每个java文件的第一句：package com.XXX.XXX.XXX.xxx;
                        // 父包名配置
                        .parent(parentPackageName)
                        .entity("po")
                        .mapper("dao")
                        .service("service")
                        .serviceImpl("service.impl")
//                        .other("other")
                        // 阶段2：所有文件的生成路径配置
                        .pathInfo(
                                new HashMap<OutputFile, String>() {{
                                    // 实体类的保存路径
                                    put(OutputFile.entity, entityPath);
                                    // mapper接口的保存路径
                                    put(OutputFile.mapper, mapperPath);
                                    // mapper.xml文件的保存路径
                                    put(OutputFile.xml, mapperXmlPath);
                                    // service层接口的保存路径
                                    put(OutputFile.service, servicePath);
                                    // service层接口实现类的保存路径
                                    put(OutputFile.serviceImpl, serviceImplPath);
                                    // 控制类的保存路径
                                    put(OutputFile.controller, controllerPath);
                                }}
                        )
                )
                .strategyConfig((scanner, builder) -> builder
                        // 根据指定的表名范围生成对应的类
                        .addInclude(tables)
                        // 去掉表中的前缀
                        .addTablePrefix(tablePrefix)

                        // 阶段1：Entity实体类策略配置
                        .entityBuilder()
                        // 开启覆盖已生成的文件
//                        .enableFileOverride()
                        // 主键类型设置为自动增长
                        .idType(IdType.AUTO)

                        // 设置父类。会在生成的实体类名后：extends BaseEntity
                        // .superClass(BaseEntity.class)
                        // 禁用生成 serialVersionUID。（不推荐禁用）
                         .disableSerialVersionUID()
                        // 开启生成字段常量。
                        // 会在实体类末尾生成一系列 [public static final String NICKNAME = "nickname";] 的语句。（一般在写wapper时，会用到）
                        // .enableColumnConstant()
                        // 开启链式模型。
                        // 会在实体类前添加 [@Accessors(chain = true)] 注解。用法如 [User user=new User().setAge(31).setName("snzl");]（这是Lombok的注解，需要添加Lombok依赖）
                        .enableChainModel()
                        // 开启 lombok 模型。
                        // 会在实体类前添加 [@Getter] 和 [@Setter] 注解。（这是Lombok的注解，需要添加Lombok依赖）
                        .enableLombok()
                        // 开启 Boolean 类型字段移除 is 前缀。
                        // .enableRemoveIsPrefix()
                        // 开启生成实体时生成字段注解。
                        // 会在实体类的属性前，添加[@TableField("nickname")]
                        .enableTableFieldAnnotation()
                        // 逻辑删除字段名(数据库)。
//                        .logicDeleteColumnName("is_delete")
                        // 逻辑删除属性名(实体)。
                        // 会在实体类的该字段属性前加注解[@TableLogic]
//                        .logicDeletePropertyName("isDelete")
                        // 数据库表映射到实体的命名策略(默认下划线转驼峰)。一般不用设置
                        // .naming(NamingStrategy.underline_to_camel)
                        // 数据库表字段映射到实体的命名策略(默认为 null，未指定按照 naming 执行)。一般不用设置
                        // .columnNaming(NamingStrategy.underline_to_camel)
                        // 添加父类公共字段。
                        // 这些字段不会出现在新增的实体类中。
//                        .addSuperEntityColumns("id", "delete_time")
                        // 添加忽略字段。
                        // 这些字段不会出现在新增的实体类中。
                        // .addIgnoreColumns("password")
                        // 添加表字段填充
                        // 会在实体类的该字段上追加注解[@TableField(value = "create_time", fill = FieldFill.INSERT)]
                        .addTableFills(new Column("created_time", FieldFill.INSERT))
                        // 会在实体类的该字段上追加注解[@TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)]
                        .addTableFills(new Column("updated_time", FieldFill.INSERT_UPDATE))
                        // 全局主键类型。如果MySQL主键设置为自增，则不需要设置此项。
                        // .idType(IdType.AUTO)
                        // 格式化文件名称。
                        // 如果不设置，如表[sys_user]的实体类名是[SysUser]。设置成下面这样，将是[SysUserEntity]
                        .formatFileName("%sPo")

                        // 阶段2：Mapper策略配置
                        .mapperBuilder()
                        // 开启覆盖已生成的文件
//                        .enableFileOverride()
                        // 设置父类
                        // 会在mapper接口方法集成[extends BaseMapper<XXXEntity>]
                        .superClass(BaseMapper.class)
                        // 开启 @Mapper 注解。
                        // 会在mapper接口上添加注解[@Mapper]
//                        .enableMapperAnnotation()
                        // 启用 BaseResultMap 生成。
                        // 会在mapper.xml文件生成[通用查询映射结果]配置。
                        .enableBaseResultMap()
                        // 启用 BaseColumnList。
                        // 会在mapper.xml文件生成[通用查询结果列 ]配置
                        .enableBaseColumnList()
                        // 设置缓存实现类
                        // .cache(MyMapperCache.class)
                        // 格式化 mapper 文件名称。
                        // 如果不设置，如表[sys_user]，默认的文件名是[SysUserMapper]。写成下面这种形式后，将变成[SysUserDao]。
                        // .formatMapperFileName("%sDao")
                        // 格式化 xml 实现类文件名称。
                        // 如果不设置，如表[sys_user]，默认的文件名是[SysUserMapper.xml]，写成下面这种形式后，将变成[SysUserXml.xml]。
                        // .formatXmlFileName("%sXml")

                        // 阶段3：Service策略配置
                         .serviceBuilder()
//                        .enableFileOverride()
                        // 设置 service 接口父类
                         .superServiceClass(IService.class)
                        // 设置 service 实现类父类
                         .superServiceImplClass(ServiceImpl.class)
                        // 格式化 service 接口文件名称
                        // 如果不设置，如表[sys_user]，默认的是[ISysUserService]。写成下面这种形式后，将变成[SysUserService]。
                         .formatServiceFileName("%sService")
                        // 格式化 service 实现类文件名称
                        // 如果不设置，如表[sys_user]，默认的是[SysUserServiceImpl]。
                         .formatServiceImplFileName("%sServiceImpl")

                        // 阶段4：Controller策略配置
//                        .controllerBuilder()
                        // 设置父类。
                        // 会集成此父类。
                        // .superClass(BaseController.class)
                        // 开启生成 @RestController 控制器
                        // 会在控制类中加[@RestController]注解。
//                        .enableRestStyle()
                        // 开启驼峰转连字符
//                        .enableHyphenStyle()

                        // 最后：构建
                        .build()
                )

                //模板引擎配置，默认 Velocity 可选模板引擎 Beetl 或 Freemarker
                //.templateEngine(new BeetlTemplateEngine())
                .templateEngine(new FreemarkerTemplateEngine())

                // 执行
                .execute();
    }
}
