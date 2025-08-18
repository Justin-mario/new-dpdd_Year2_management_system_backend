package com.mkcollege.dpdd_students_management_system.data;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

@SpringBootTest
@ActiveProfiles("test")
public class DatabaseConnectivityTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private DataSource dataSource;

    private static Connection connection;

    @Test
    void testDatabaseConnectionIsAlive() {
        try {
            // Get and hold connection to ensure proper cleanup
            connection = dataSource.getConnection();

            // Test simple query execution
            Integer result = jdbcTemplate.queryForObject("SELECT 1", Integer.class);
            assertTrue(result == 1, "Database should return 1 for SELECT 1 query");

            // Test database metadata
            String dbProduct = connection.getMetaData().getDatabaseProductName();
            System.out.println("Connected to: " + dbProduct);
            assertTrue(!dbProduct.isEmpty(), "Should retrieve database product name");

        } catch (Exception e) {
            fail("Database connection failed: " + e.getMessage());
        }
    }

    @Test
    void testTableCreation() {
        try {
            // Create test table
            jdbcTemplate.execute("CREATE TABLE test_table (id INT PRIMARY KEY, name VARCHAR(255))");

            // Insert test data
            jdbcTemplate.execute("INSERT INTO test_table (id, name) VALUES (1, 'test')");

            // Verify data retrieval
            String name = jdbcTemplate.queryForObject(
                    "SELECT name FROM test_table WHERE id = 1", String.class);

            assertTrue("test".equals(name), "Should retrieve inserted data");

        } catch (Exception e) {
            fail("Table operations failed: " + e.getMessage());
        }
    }

    @AfterAll
    public static void cleanUp() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            System.out.println("Closing database connection...");
            connection.close();
        }
    }
}