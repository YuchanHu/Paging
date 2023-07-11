package com.yuchan.usermanager.Utils;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class TestUser {
    public static void main(String[] args) throws SQLException {
        String sql = "insert into user(name, pwd, email, birthday) values (?, ?, ?, ?)";
        Connection conn = JDBCUtil.getConnection();
        PreparedStatement ps = conn.prepareStatement(sql);

        Random random = new Random();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        for (int i = 0; i <= 200; i++) {
            String name = generateRandomName();
            String pwd = generateRandomPassword();
            String email = generateRandomEmail();
            String birthday = generateRandomBirthday();

            ps.setString(1, name);
            ps.setString(2, pwd);
            ps.setString(3, email);
            ps.setString(4, birthday);
            ps.executeUpdate();
        }

        JDBCUtil.close(ps);
        JDBCUtil.close(conn);
    }

    private static String generateRandomName() {
        StringBuilder nameBuilder = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < 5; i++) {
            char c = (char) (random.nextInt(26) + 'a');
            nameBuilder.append(c);
        }

        return nameBuilder.toString();
    }

    private static String generateRandomPassword() {
        StringBuilder pwdBuilder = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < 6; i++) {
            char c = (char) (random.nextInt(26) + 'a');
            pwdBuilder.append(c);
        }

        return pwdBuilder.toString();
    }

    private static String generateRandomEmail() {
        StringBuilder emailBuilder = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < 10; i++) {
            int digit = random.nextInt(10);
            emailBuilder.append(digit);
        }

        emailBuilder.append("@qq.com");
        return emailBuilder.toString();
    }

    private static String generateRandomBirthday() {
        long offset = Timestamp.valueOf("1970-01-01 00:00:00").getTime();
        long end = Timestamp.valueOf("2003-12-31 23:59:59").getTime();
        long diff = end - offset + 1;
        long randomTimestamp = offset + (long) (Math.random() * diff);

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(new Date(randomTimestamp));
    }
}
