package test;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import test.DBUtils;
import javax.annotation.Resource;
import javax.sql.DataSource;

public class MembershipDAO {
    
    public MembershipDAO() {
        // Constructor can be left empty if you're using dependency injection
    }

   

    public List<Membership> getAllMemberships() throws SQLException {
        List<Membership> memberships = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = DBUtils.getConnection();

            // Replace with your actual query to retrieve all memberships
            String query = "SELECT * FROM Membership";

            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int membershipId = resultSet.getInt("membership_id");
                int customerId = resultSet.getInt("customer_id");
                Date startDate = resultSet.getDate("start_date");
                Date endDate = resultSet.getDate("end_date");
                String type = resultSet.getString("type");

                Membership membership = new Membership(membershipId, customerId, startDate, endDate, type);
                memberships.add(membership);
            }

        }catch(Exception e) {}
        return memberships;}

    public Membership getMembershipById(int membershipId) throws SQLException {
        Membership membership = null;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = DBUtils.getConnection();

            String query = "SELECT * FROM Membership WHERE membership_id = ?";
            statement = connection.prepareStatement(query);
            statement.setInt(1, membershipId);

            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int customerId = resultSet.getInt("customer_id");
                Date startDate = resultSet.getDate("start_date");
                Date endDate = resultSet.getDate("end_date");
                String type = resultSet.getString("type");

                membership = new Membership(membershipId, customerId, startDate, endDate, type);
            }

        } catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
        return membership;
    }
    public boolean membershipExists(int membershipId) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = DBUtils.getConnection();
            String query = "SELECT COUNT(*) FROM Membership WHERE membership_id = ?";
            statement = connection.prepareStatement(query);
            statement.setInt(1, membershipId);
            resultSet = statement.executeQuery();
            
            // Check if any rows were returned by the query
            if (resultSet.next()) {
                int count = resultSet.getInt(1);
                // If count > 0, membership exists
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        // If an exception occurred or no rows were returned, membership does not exist
        return false;
    }

    public boolean updateMembership(int membershipId,int customerId, java.util.Date startDate, java.util.Date endDate, String type) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = DBUtils.getConnection();
           // out.println("Datbase Connectes..");
            String query = "UPDATE Membership SET customer_id=?, start_date = ?, end_date = ?, type = ? WHERE membership_id = ?";
            statement = connection.prepareStatement(query);
            statement.setInt(1, customerId);
            statement.setDate(2, new java.sql.Date(startDate.getTime()));
            statement.setDate(3, new java.sql.Date(endDate.getTime()));
            statement.setString(4, type);
            statement.setInt(5, membershipId);

            int rowsUpdated = statement.executeUpdate();
            return rowsUpdated > 0; 
            //request.getRequestDispatcher
            // Return true if rows were updated, false otherwise

        } catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
        return true;
    }
    public boolean deleteMembershipById(int membershipId) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = DBUtils.getConnection();

            String query = "DELETE FROM Membership WHERE membership_id = ?";
            statement = connection.prepareStatement(query);
            statement.setInt(1, membershipId);

            int rowsDeleted = statement.executeUpdate();
            return rowsDeleted > 0;
            

        } catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
            close(connection, statement, null);
        }
        return true;
    }

    private void close(Connection connection, PreparedStatement statement, ResultSet resultSet) {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
