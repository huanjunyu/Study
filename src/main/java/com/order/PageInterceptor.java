package com.order;


import com.order.model.Common;
import com.order.model.Order;
import org.apache.ibatis.executor.resultset.ResultSetHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.RowBounds;
import org.omg.CORBA.*;


import java.lang.Object;
import java.lang.annotation.Annotation;
import java.sql.*;
import java.util.Properties;


import static org.apache.ibatis.reflection.SystemMetaObject.DEFAULT_OBJECT_WRAPPER_FACTORY;


/**
 * @ClassName: PageInterceptor
 * @Description:TODO(这里用一句话描述这个类的作用)
 * @author: H_jy
 * @date: 2019-04-02 14:34
 */
@Intercepts({@Signature(type = StatementHandler.class, method = "prepare", args = {Connection.class,Integer.class}),
             @Signature(type = ResultSetHandler.class, method = "handleResultSets", args = {Statement.class})})
public class PageInterceptor implements Interceptor  {

    private static final String SELECT_ID="getorderbypage";

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        System.out.println("PageInterceptor -- intercept");

        if (invocation.getTarget() instanceof StatementHandler) {
            StatementHandler statementHandler = (StatementHandler) invocation.getTarget();
            MetaObject metaStatementHandler = SystemMetaObject.forObject(statementHandler);
            MappedStatement mappedStatement=(MappedStatement) metaStatementHandler.getValue("delegate.mappedStatement");
            String selectId=mappedStatement.getId();
            if(selectId.substring(selectId.lastIndexOf(".")+1).toLowerCase().contains(SELECT_ID)){
                BoundSql boundSql = (BoundSql) metaStatementHandler.getValue("delegate.boundSql");
                // 分页参数作为参数对象parameterObject的一个属性
                String sql = boundSql.getSql();
                Common co=(Common)(boundSql.getParameterObject());

                // 重写sql
                String countSql=concatCountSql(sql);
                String pageSql=concatPageSql(sql,co);

                System.out.println("重写的 count  sql        :"+countSql);
                System.out.println("重写的 select sql        :"+pageSql);

                Connection connection = (Connection) invocation.getArgs()[0];

                PreparedStatement countStmt = null;
                ResultSet rs = null;
                int totalCount = 0;
                try {
                    countStmt = connection.prepareStatement(countSql);
                    rs = countStmt.executeQuery();
                    if (rs.next()) {
                        totalCount = rs.getInt(1);

                    }
                    try {
                        rs.close();
                        countStmt.close();
                    } catch (SQLException e) {
                        System.out.println("Ignore this exception"+ e);
                    }

                } catch (SQLException e) {
                    System.out.println("Ignore this exception"+e);
                }

                metaStatementHandler.setValue("delegate.boundSql.sql", pageSql);

                //绑定count
                System.out.println("totalCount:"+totalCount);
                co.setCount(totalCount);
            }
        }

        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        if (target instanceof StatementHandler) {
            return Plugin.wrap(target,this);
        } else {
            return target;
        }
    }

    @Override
    public void setProperties(Properties properties) {

    }

    public String concatCountSql(String sql){
        StringBuffer sb=new StringBuffer("select count(*) from ");
        sql=sql.toLowerCase();
        System.out.println("sql:"+sql);
        if(sql.lastIndexOf("order")>sql.lastIndexOf(")")){
            sb.append(sql.split("from")[1]);
        }else{
            sb.append(sql.substring(sql.indexOf("from")+4));
        }
        return sb.toString();
    }

    public String concatPageSql(String sql,Common co){
        StringBuffer sb=new StringBuffer();
        sb.append(sql);
        sb.append(" limit ").append(co.getPagebegin()).append(" , ").append(co.getPagesize());
        return sb.toString();
    }

    public void setPageCount(){

    }
}
