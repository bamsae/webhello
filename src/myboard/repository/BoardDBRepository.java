package myboard.repository;

import myboard.entity.Board;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by bungubbang
 * Email: bungubbang57@gmail.com
 */
public class BoardDBRepository implements BoardRepository {

    private static BoardDBRepository instance = new BoardDBRepository();

    public static BoardDBRepository getInstance() {
        return instance;
    }

    private String url = "jdbc:postgresql://localhost:5432/study";
    private String usr = "study";
    private String pwd = "1234";

    @Override
    public List<Board> getBoards() {
        List<Board> boards = new ArrayList<Board>();
        try {
            Connection connection = DriverManager.getConnection(url, usr, pwd);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from board order by id desc");
            while (resultSet.next()) {
                Board board = new Board();
                board.setId(resultSet.getInt("id"));
                board.setWriter(resultSet.getString("writer"));
                board.setPw(resultSet.getString("pw"));
                board.setTitle(resultSet.getString("title"));
                board.setContent(resultSet.getString("content"));

                boards.add(board);
            }

            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return boards;
    }

    @Override
    public Board FindById(int id) {
        Board board = new Board();
        try {
            Connection connection = DriverManager.getConnection(url, usr, pwd);
            PreparedStatement preparedStatement = connection.prepareStatement("select * from board where id=?");
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();

            board.setId(resultSet.getInt("id"));
            board.setWriter(resultSet.getString("writer"));
            board.setPw(resultSet.getString("pw"));
            board.setTitle(resultSet.getString("title"));
            board.setContent(resultSet.getString("content"));

            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return board;
    }

    @Override
    public void addBoard(Board board) {
        try {
            Connection connection = DriverManager.getConnection(url, usr, pwd);
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO board(writer, pw, title, content) VALUES (?, ?, ?, ?)");
            preparedStatement.setString(1, board.getWriter());
            preparedStatement.setString(2, board.getPw());
            preparedStatement.setString(3, board.getTitle());
            preparedStatement.setString(4, board.getContent());

            preparedStatement.executeUpdate();

            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void modifyBoard(Board board) {
        try {
            Connection connection = DriverManager.getConnection(url, usr, pwd);
            PreparedStatement preparedStatement = connection.prepareStatement("update board set writer=?, pw=?, title=?, content=? where id=?");
            preparedStatement.setString(1, board.getWriter());
            preparedStatement.setString(2, board.getPw());
            preparedStatement.setString(3, board.getTitle());
            preparedStatement.setString(4, board.getContent());
            preparedStatement.setInt(5, board.getId());

            preparedStatement.executeUpdate();

            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteBoard(int id) {
        try {
            Connection connection = DriverManager.getConnection(url, usr, pwd);
            PreparedStatement preparedStatement = connection.prepareStatement("delete from board where id=?");
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
