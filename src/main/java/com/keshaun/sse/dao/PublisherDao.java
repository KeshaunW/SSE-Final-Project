package com.keshaun.sse.dao;

import com.keshaun.sse.model.Publisher;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PublisherDao implements Dao<Publisher> {
    @Override
    public void add(Publisher publisher) throws SQLException {
        PreparedStatement prepared = conn.prepareStatement("INSERT INTO tbl_publisher(publisherName, publisherAddress, publisherPhone) VALUES (?, ?, ?)");
        prepared.setString(1, publisher.getPublisherName());
        prepared.setString(2, publisher.getPublisherAddress());
        prepared.setString(3, publisher.getPublisherPhone());
        prepared.executeUpdate();
    }

    @Override
    public Publisher getSingle(int id) throws SQLException {
        Publisher publisher = null;

        PreparedStatement prepared = conn.prepareStatement("SELECT * FROM tbl_publisher WHERE publisherId = ?");
        prepared.setInt(1, id);

        ResultSet result = prepared.executeQuery();
        if (result.next()) {
            publisher = new Publisher(result.getString("publisherName"), result.getString("publisherAddress"), result.getString("publisherPhone"));
            publisher.setPublisherId(id);
        }

        return publisher;
    }

    @Override
    public List<Publisher> getAll() throws SQLException {
        List<Publisher> publishers = new ArrayList<>();

        ResultSet result = conn.createStatement().executeQuery("SELECT * FROM tbl_publisher");
        while (result.next()) {
            Publisher publisher = new Publisher(result.getString("publisherName"), result.getString("publisherAddress"), result.getString("publisherPhone"));
            publisher.setPublisherId(result.getInt("publisherId"));
            publishers.add(publisher);
        }

        return publishers;
    }

    @Override
    public void update(Publisher publisher) throws SQLException {
        PreparedStatement prepared = conn.prepareStatement("UPDATE tbl_publisher SET publisherName = ?, publisherAddress = ?, publisherPhone = ? WHERE publisherId = ?");
        prepared.setString(1, publisher.getPublisherName());
        prepared.setString(2, publisher.getPublisherAddress());
        prepared.setString(3, publisher.getPublisherPhone());
        prepared.setInt(4, publisher.getPublisherId());
        prepared.executeUpdate();
    }

    @Override
    public void delete(int id) throws SQLException {
        PreparedStatement prepared = conn.prepareStatement("DELETE FROM tbl_publisher WHERE publisherId = ?");
        prepared.setInt(1, id);
        prepared.executeUpdate();
    }

    @Override
    public void delete(Publisher publisher) throws SQLException {
        delete(publisher.getPublisherId());
    }
}
