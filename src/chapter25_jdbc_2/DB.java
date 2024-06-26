package chapter25_jdbc_2;

import java.sql.*;

public class DB {
    Connection conn = null; // DB와 자바프로그램과 연결을 하기 위해 사용
    PreparedStatement preparedStatement = null; // 쿼리문을 임시로 저장하는 ? 용도? 일단을 이해 .저장 및 실행?
    ResultSet resultSet = null; // 쿼리시에 결과를 저장하는 용도로 사용. select 에 주로 사용 // 쿼리문을 실행하고 저장하는 용도

    public void connectDB() {
        final String driver = "org.mariadb.jdbc.Driver";
        final String DB_HOST = "127.0.0.1"; // localhost 주소
        final String DB_PORT = "3306";  // Port 번호
        final String DB_NAME = "sample_java2";  // 데이터 베이스 이름    // DB 명
        final String DB_URL = "jdbc:mariadb://" + DB_HOST + ":" + DB_PORT + "/" + DB_NAME; // 첫번째 매개값. 연결 문자열
        final String DB_USER = "root";
        final String DB_PASS = "3033";

        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
            if (conn != null) {
                System.out.println("DB 접속 성공");
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("DB 접속 실패");
            e.printStackTrace();
        }

    }

    public void closeDB() {
        /* 데이터베이스 연결 해제 */
//                System.out.println("result 객체주소 : "+resultSet);  // 테스트 코드
//                System.out.println("preparedStatement 객체주소 : "+preparedStatement); // 테스트 코드
        try {
            if (resultSet != null) {    // 열린 경우 닫음
                resultSet.close();
            }
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (conn != null && !conn.isClosed()) {
                conn.close();
                System.out.println("DB 접속 해제");
            }
        } catch (SQLException e) {  // DB를 끊을 때 SQLException 이 발생할 수 있어서 예외 처리가 필요하다.
            e.printStackTrace();
        }
    }
}
