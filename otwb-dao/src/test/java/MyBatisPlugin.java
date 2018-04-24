import org.apache.commons.lang3.StringUtils;
import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.*;
import org.mybatis.generator.api.dom.xml.Document;
import org.mybatis.generator.api.dom.xml.XmlElement;
import org.mybatis.generator.config.CommentGeneratorConfiguration;
import org.mybatis.generator.config.Context;
import org.mybatis.generator.internal.util.StringUtility;

import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * MyBatisPlugin 自动读取建表的注释 使用lombok 注解getter setter
 *
 * @author <a href="yogurt_lei@foxmail.com">Yogurt_lei</a>
 * @version v1.0 , 2018-04-22 15:14
 */
public class MyBatisPlugin extends PluginAdapter {
    private Set<String> mappers = new HashSet<String>();
    private boolean caseSensitive = false;
    //开始的分隔符，例如mysql为`，sqlserver为[
    private String beginningDelimiter = "";
    //结束的分隔符，例如mysql为`，sqlserver为]
    private String endingDelimiter = "";
    //数据库模式
    private String schema;
    //注释生成器
    private CommentGeneratorConfiguration commentCfg;

    private String getDelimiterName(String name) {
        StringBuilder nameBuilder = new StringBuilder();
        if (StringUtility.stringHasValue(schema)) {
            nameBuilder.append(schema);
            nameBuilder.append(".");
        }
        nameBuilder.append(beginningDelimiter);
        nameBuilder.append(name);
        nameBuilder.append(endingDelimiter);
        return nameBuilder.toString();
    }

    public boolean validate(List<String> list) {
        return true;
    }

    /**
     * 处理实体类的包和@Table注解
     */
    private void processEntityClass(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        //引入JPA注解
        topLevelClass.addImportedType("javax.persistence.*");
        topLevelClass.addImportedType("org.springframework.format.annotation.DateTimeFormat");
        String tableName = introspectedTable.getFullyQualifiedTableNameAtRuntime();
        //引入lombok
        topLevelClass.addImportedType("lombok.Data");
        topLevelClass.addImportedType("lombok.EqualsAndHashCode");
        topLevelClass.addAnnotation("@Data");
        topLevelClass.addAnnotation("@EqualsAndHashCode(callSuper = true)");
        //如果包含空格，或者需要分隔符，需要完善
        if (StringUtility.stringContainsSpace(tableName)) {
            tableName = context.getBeginningDelimiter() + tableName + context.getEndingDelimiter();
        }

        //是否忽略大小写，对于区分大小写的数据库，会有用
        if (caseSensitive && !topLevelClass.getType().getShortName().equals(tableName)) {
            topLevelClass.addAnnotation("@Table(name = \"" + getDelimiterName(tableName) + "\")");
        } else if (!topLevelClass.getType().getShortName().equalsIgnoreCase(tableName)) {
            topLevelClass.addAnnotation("@Table(name = \"" + getDelimiterName(tableName) + "\")");
        } else if (StringUtility.stringHasValue(schema) || StringUtility.stringHasValue(beginningDelimiter) || StringUtility.stringHasValue(endingDelimiter)) {
            topLevelClass.addAnnotation("@Table(name = \"" + getDelimiterName(tableName) + "\")");
        } else {
            topLevelClass.addAnnotation("@Table(name = \"" + getDelimiterName(tableName) + "\")");
        }
    }

    /**
     * 每个实体类生成ToVO方法
     */
    private void generateToVO(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        String shortName = topLevelClass.getType().getShortName();
        String entityObjectName = Character.toLowerCase(shortName.charAt(0)) + StringUtils.substring(shortName, 1);

        // 方法头部
        Method method = new Method();
        method.setVisibility(JavaVisibility.PUBLIC);
        method.setReturnType(new FullyQualifiedJavaType(shortName + "VO"));
        method.setName("toVO");
        method.addAnnotation("@Override");
        // 生成注释
        context.getCommentGenerator().addGeneralMethodComment(method, introspectedTable);
        // 拼接方法体内容
        StringBuilder sb = new StringBuilder();
        sb.append(shortName).append("VO ").append(entityObjectName)
                .append(" = new ").append(shortName).append("VO();\n");
        method.addBodyLine(sb.toString());
        StringBuilder filedNameBuilder = new StringBuilder();

        for (Field field : topLevelClass.getFields()) {
            String property = field.getName();
            sb.setLength(0);

            filedNameBuilder.append(Character.toUpperCase(property.charAt(0))).append(StringUtils.substring(property,1));
            sb.append("Optional.ofNullable(this.get").append(filedNameBuilder.toString())
                    .append("()).ifPresent(").append(entityObjectName).append("::set").append(filedNameBuilder.toString()).append(");");
            filedNameBuilder.setLength(0);

            method.addBodyLine(sb.toString());
        }

        // 加载通用属性设置
        // 加载通用属性设置
        method.addBodyLine("Optional.ofNullable(this.getStatus()).ifPresent(" + entityObjectName + "::setStatus);");
        method.addBodyLine("Optional.ofNullable(this.getDelFlag()).ifPresent(" + entityObjectName + "::setDelFlag);");
        method.addBodyLine("Optional.ofNullable(this.getCreateDate()).ifPresent(" + entityObjectName + "::setCreateDate);");
        method.addBodyLine("Optional.ofNullable(this.getCreateUser()).ifPresent(" + entityObjectName + "::setCreateUser);");
        method.addBodyLine("Optional.ofNullable(this.getModifyDate()).ifPresent(" + entityObjectName + "::setModifyDate);");
        method.addBodyLine("Optional.ofNullable(this.getModifyUser()).ifPresent(" + entityObjectName + "::setModifyUser);");

        method.addBodyLine("");
        method.addBodyLine("return " + entityObjectName + ";");

        topLevelClass.addMethod(method);
    }

    /**
     * 每个实体类生成对应的ViewObject类  自己新建java文件复制生成的类
     */
    private void generateViewObject(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        String shortName = topLevelClass.getType().getShortName();
        TopLevelClass viewObject = new TopLevelClass(shortName+"VO");
        viewObject.setSuperClass("ViewObject<"+shortName+">");
        viewObject.setVisibility(JavaVisibility.PUBLIC);

        //引入lombok
        viewObject.addAnnotation("@Data");
        viewObject.addAnnotation("@ApiModel(\"\")");
        viewObject.addAnnotation("@EqualsAndHashCode(callSuper = true)");

        for (Field field : topLevelClass.getFields()) {
            Field fieldVO = new Field(field.getName(), field.getType());
            if (field.getName().equals("id")) {
                fieldVO.addAnnotation("@ApiModelProperty(hidden = true)");
            } else {
                String commentBuilder = "";
                List<String> javaDocLines = field.getJavaDocLines();
                String comment = javaDocLines.stream().collect(Collectors.joining());
                comment = StringUtils.replaceAll(comment, "(\\s+|\\*|/)", "");

                fieldVO.addAnnotation("@ApiModelProperty(\""+comment+"\")");

                // if ("Date".equals(field.getType().getShortName())) {
                //     fieldVO.addAnnotation("@DateTimeFormat(pattern = \"yyyy-MM-dd HH:mm:ss\")");
                // }
            }
            viewObject.addField(fieldVO);
        }
        topLevelClass.addInnerClass(viewObject);

        // 生成toEntity方法
        Method method = new Method();
        method.setVisibility(JavaVisibility.PUBLIC);
        method.setReturnType(new FullyQualifiedJavaType(shortName));
        method.setName("toEntity");
        method.addAnnotation("@Override");

        // 生成注释
        context.getCommentGenerator().addGeneralMethodComment(method, introspectedTable);
        // 拼接方法体内容
        StringBuilder sb = new StringBuilder();

        String entityObjectName = Character.toLowerCase(shortName.charAt(0)) + StringUtils.substring(shortName, 1);
        sb.append(shortName).append(" ").append(entityObjectName).append(" = new ").append(shortName).append("();\n");
        method.addBodyLine(sb.toString());
        StringBuilder filedNameBuilder = new StringBuilder();

        for (Field field : topLevelClass.getFields()) {
            String property = field.getName();
            sb.setLength(0);

            filedNameBuilder.append(Character.toUpperCase(property.charAt(0))).append(StringUtils.substring(property,1));
            sb.append("Optional.ofNullable(this.get").append(filedNameBuilder.toString())
                    .append("()).ifPresent(").append(entityObjectName).append("::set").append(filedNameBuilder.toString()).append(");");
            filedNameBuilder.setLength(0);

            method.addBodyLine(sb.toString());
        }

        // 加载通用属性设置
        method.addBodyLine("Optional.ofNullable(this.getStatus()).ifPresent(" + entityObjectName + "::setStatus);");
        method.addBodyLine("Optional.ofNullable(this.getDelFlag()).ifPresent(" + entityObjectName + "::setDelFlag);");
        method.addBodyLine("Optional.ofNullable(this.getCreateDate()).ifPresent(" + entityObjectName + "::setCreateDate);");
        method.addBodyLine("Optional.ofNullable(this.getCreateUser()).ifPresent(" + entityObjectName + "::setCreateUser);");
        method.addBodyLine("Optional.ofNullable(this.getModifyDate()).ifPresent(" + entityObjectName + "::setModifyDate);");
        method.addBodyLine("Optional.ofNullable(this.getModifyUser()).ifPresent(" + entityObjectName + "::setModifyUser);");

        method.addBodyLine("");
        method.addBodyLine("return " + entityObjectName + ";");

        viewObject.addMethod(method);
    }

    /**
     * 生成带BLOB字段的对象
     */
    @Override
    public boolean modelRecordWithBLOBsClassGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        processEntityClass(topLevelClass, introspectedTable);
        generateToVO(topLevelClass, introspectedTable);
        generateViewObject(topLevelClass, introspectedTable);
        return true;
    }

    /**
     * 生成实体类注解KEY对象
     */
    @Override
    public boolean modelPrimaryKeyClassGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        processEntityClass(topLevelClass, introspectedTable);
        generateToVO(topLevelClass, introspectedTable);
        generateViewObject(topLevelClass, introspectedTable);
        return true;
    }

    /**
     * 生成基础实体类
     */
    @Override
    public boolean modelBaseRecordClassGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        processEntityClass(topLevelClass, introspectedTable);
        generateToVO(topLevelClass, introspectedTable);
        generateViewObject(topLevelClass, introspectedTable);
        return true;
    }

    private Pattern globalColumnPattern = Pattern.compile("^(CREATE_DATE|CREATE_USER|MODIFY_DATE|MODIFY_USER|DEL_FLAG|STATUS)$",Pattern.CASE_INSENSITIVE);
    /**
     * 继承了自己的BaseModel 所以生成的model中不再定义通用的几个字段,
     * 通用字段有 createData updateData createUser updateUser status delFlag
     */
    @Override
    public boolean modelFieldGenerated(Field field, TopLevelClass topLevelClass, IntrospectedColumn
            introspectedColumn, IntrospectedTable introspectedTable, ModelClassType modelClassType) {
        return !globalColumnPattern.matcher(introspectedColumn.getActualColumnName()).find();
    }

    /**
     * 生成的Mapper接口
     */
    @Override
    public boolean clientGenerated(Interface interfaze, TopLevelClass topLevelClass, IntrospectedTable
            introspectedTable) {
        //获取实体类
        FullyQualifiedJavaType entityType = new FullyQualifiedJavaType(introspectedTable.getBaseRecordType());
        //import接口
        for (String mapper : mappers) {
            interfaze.addImportedType(new FullyQualifiedJavaType(mapper));
            interfaze.addSuperInterface(new FullyQualifiedJavaType(mapper + "<" + entityType.getShortName() + ">"));
        }
        //import实体类
        interfaze.addImportedType(entityType);
        return true;
    }

    /**
     * //设置默认的注释生成器
     */
    @Override
    public void setContext(Context context) {
        super.setContext(context);
        commentCfg = new CommentGeneratorConfiguration();
        commentCfg.setConfigurationType(MapperCommentGenerator.class.getCanonicalName());
        context.setCommentGeneratorConfiguration(commentCfg);
        //支持oracle获取注释#114
        context.getJdbcConnectionConfiguration().addProperty("remarksReporting", "true");
    }

    @Override
    public void setProperties(Properties properties) {
        super.setProperties(properties);
        String mappers = this.properties.getProperty("mappers");
        if (StringUtility.stringHasValue(mappers)) {
            for (String mapper : mappers.split(",")) {
                this.mappers.add(mapper);
            }
        } else {
            throw new RuntimeException("Mapper插件缺少必要的mappers属性!");
        }
        String caseSensitive = this.properties.getProperty("caseSensitive");
        if (StringUtility.stringHasValue(caseSensitive)) {
            this.caseSensitive = caseSensitive.equalsIgnoreCase("TRUE");
        }
        String beginningDelimiter = this.properties.getProperty("beginningDelimiter");
        if (StringUtility.stringHasValue(beginningDelimiter)) {
            this.beginningDelimiter = beginningDelimiter;
        }
        String endingDelimiter = this.properties.getProperty("endingDelimiter");
        if (StringUtility.stringHasValue(endingDelimiter)) {
            this.endingDelimiter = endingDelimiter;
        }
        String schema = this.properties.getProperty("schema");
        if (StringUtility.stringHasValue(schema)) {
            this.schema = schema;
        }

        commentCfg.addProperty("beginningDelimiter", this.beginningDelimiter);
        commentCfg.addProperty("endingDelimiter", this.endingDelimiter);
    }

    // 拼装SQL语句生成Mapper接口映射文件
    @Override
    public boolean sqlMapDocumentGenerated(Document document, IntrospectedTable introspectedTable) {
        return super.sqlMapDocumentGenerated(document, introspectedTable);
    }

    // 屏蔽getter setter 使用lombok
    @Override
    public boolean modelSetterMethodGenerated(Method method, TopLevelClass topLevelClass, IntrospectedColumn
            introspectedColumn, IntrospectedTable introspectedTable, ModelClassType modelClassType) {
        return false;
    }

    @Override
    public boolean modelGetterMethodGenerated(Method method, TopLevelClass topLevelClass, IntrospectedColumn
            introspectedColumn, IntrospectedTable introspectedTable, ModelClassType modelClassType) {
        return false;
    }

    //下面所有return false的方法都不生成。这些都是基础的CRUD方法，使用通用Mapper实现
    @Override
    public boolean clientDeleteByPrimaryKeyMethodGenerated(Method method, TopLevelClass topLevelClass,
                                                           IntrospectedTable introspectedTable) {
        return false;
    }

    @Override
    public boolean clientInsertMethodGenerated(Method method, TopLevelClass topLevelClass, IntrospectedTable
            introspectedTable) {
        return false;
    }

    @Override
    public boolean clientInsertSelectiveMethodGenerated(Method method, TopLevelClass topLevelClass, IntrospectedTable
            introspectedTable) {
        return false;
    }

    @Override
    public boolean clientSelectByPrimaryKeyMethodGenerated(Method method, TopLevelClass topLevelClass,
                                                           IntrospectedTable introspectedTable) {
        return false;
    }

    @Override
    public boolean clientUpdateByPrimaryKeySelectiveMethodGenerated(Method method, TopLevelClass topLevelClass,
                                                                    IntrospectedTable introspectedTable) {
        return false;
    }

    @Override
    public boolean clientUpdateByPrimaryKeyWithBLOBsMethodGenerated(Method method, TopLevelClass topLevelClass,
                                                                    IntrospectedTable introspectedTable) {
        return false;
    }

    @Override
    public boolean clientUpdateByPrimaryKeyWithoutBLOBsMethodGenerated(Method method, TopLevelClass topLevelClass,
                                                                       IntrospectedTable introspectedTable) {
        return false;
    }

    @Override
    public boolean clientDeleteByPrimaryKeyMethodGenerated(Method method, Interface interfaze, IntrospectedTable
            introspectedTable) {
        return false;
    }

    @Override
    public boolean clientInsertMethodGenerated(Method method, Interface interfaze, IntrospectedTable
            introspectedTable) {
        return false;
    }

    @Override
    public boolean clientInsertSelectiveMethodGenerated(Method method, Interface interfaze, IntrospectedTable
            introspectedTable) {
        return false;
    }

    @Override
    public boolean clientSelectAllMethodGenerated(Method method, Interface interfaze, IntrospectedTable
            introspectedTable) {
        return false;
    }

    @Override
    public boolean clientSelectAllMethodGenerated(Method method, TopLevelClass topLevelClass, IntrospectedTable
            introspectedTable) {
        return false;
    }

    @Override
    public boolean clientSelectByPrimaryKeyMethodGenerated(Method method, Interface interfaze, IntrospectedTable
            introspectedTable) {
        return false;
    }

    @Override
    public boolean clientUpdateByPrimaryKeySelectiveMethodGenerated(Method method, Interface interfaze,
                                                                    IntrospectedTable introspectedTable) {
        return false;
    }

    @Override
    public boolean clientUpdateByPrimaryKeyWithBLOBsMethodGenerated(Method method, Interface interfaze,
                                                                    IntrospectedTable introspectedTable) {
        return false;
    }

    @Override
    public boolean clientUpdateByPrimaryKeyWithoutBLOBsMethodGenerated(Method method, Interface interfaze,
                                                                       IntrospectedTable introspectedTable) {
        return false;
    }

    @Override
    public boolean sqlMapDeleteByPrimaryKeyElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {
        return false;
    }

    @Override
    public boolean sqlMapInsertElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {
        return false;
    }

    @Override
    public boolean sqlMapInsertSelectiveElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {
        return false;
    }

    @Override
    public boolean sqlMapSelectAllElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {
        return false;
    }

    @Override
    public boolean sqlMapSelectByPrimaryKeyElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {
        return false;
    }

    @Override
    public boolean sqlMapUpdateByPrimaryKeySelectiveElementGenerated(XmlElement element, IntrospectedTable
            introspectedTable) {
        return false;
    }

    @Override
    public boolean sqlMapUpdateByPrimaryKeyWithBLOBsElementGenerated(XmlElement element, IntrospectedTable
            introspectedTable) {
        return false;
    }

    @Override
    public boolean sqlMapUpdateByPrimaryKeyWithoutBLOBsElementGenerated(XmlElement element, IntrospectedTable
            introspectedTable) {
        return false;
    }

    @Override
    public boolean providerGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        return false;
    }

    @Override
    public boolean providerApplyWhereMethodGenerated(Method method, TopLevelClass topLevelClass, IntrospectedTable
            introspectedTable) {
        return false;
    }

    @Override
    public boolean providerInsertSelectiveMethodGenerated(Method method, TopLevelClass topLevelClass,
                                                          IntrospectedTable introspectedTable) {
        return false;
    }

    @Override
    public boolean providerUpdateByPrimaryKeySelectiveMethodGenerated(Method method, TopLevelClass topLevelClass,
                                                                      IntrospectedTable introspectedTable) {
        return false;
    }
}
