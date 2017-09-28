package misa.masterchild.dao.mybatis.transaction;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class MyBatisTransaction {

    private SqlSession sqlSession;
    private SqlSessionFactory sqlSessionFactory;

    public void process(MyBatisTransactionProcessor transactionProcessor) throws IOException {
        try {
            transactionProcessor.process();
            getSqlSession().commit();
        } finally {
            getSqlSession().close();
        }
    }

    private SqlSessionFactory getSqlSessionFactory() throws IOException {
        if (sqlSessionFactory == null) {
            String resource = "mybatis/config/mybatis-config.xml";
            InputStream inputStream = Resources.getResourceAsStream(resource);
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        }
        return sqlSessionFactory;
    }

    private SqlSession getSqlSession() throws IOException {
        if (sqlSession == null) {
            sqlSession = getSqlSessionFactory().openSession();
        }
        return sqlSession;
    }

    public abstract class MyBatisTransactionProcessor {
        public abstract void process() throws IOException;
        public SqlSession openSQLSession() throws IOException {
            return getSqlSession();
        }
    }
}
