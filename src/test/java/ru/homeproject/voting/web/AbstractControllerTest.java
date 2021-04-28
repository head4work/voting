package ru.homeproject.voting.web;


import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import ru.homeproject.voting.util.JpaUtil;

import java.util.Objects;

@SpringJUnitConfig(locations = {"classpath:spring/spring-app.xml"})
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))

public abstract class AbstractControllerTest {
    @Autowired
    private CacheManager cacheManager;

    @Autowired
    private JpaUtil jpaUtil;

    @BeforeEach
    public void setUp() {
        Objects.requireNonNull(cacheManager.getCache("restaurants")).clear();
        jpaUtil.clear2ndLevelHibernateCache();
    }
}
