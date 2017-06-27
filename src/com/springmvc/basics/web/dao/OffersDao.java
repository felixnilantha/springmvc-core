package com.asta.spring.web.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component("offerDao")
public class OffersDao {

    // private JdbcTemplate jdbc;
    private NamedParameterJdbcTemplate jdbc;

    @Autowired
    public void setDataSource(DataSource jdbc) {
        // this.jdbc = new JdbcTemplate(jdbc);
        this.jdbc = new NamedParameterJdbcTemplate(jdbc);
    }

    public List<Offer> getOffers() {

        return jdbc.query("select * from offers, users where offers.username=users.username and users.enabled=true", new OfferRowMapper());

    }

    public List<Offer> getOffers(String username) {

        return jdbc.query("select * from offers, users where offers.username=users.username and users.enabled=true and offers.username=:username", new MapSqlParameterSource("username", username),
                new OfferRowMapper());

    }

    @Transactional
    public int[] create(List<Offer> offer) {

        SqlParameterSource[] params = SqlParameterSourceUtils.createBatch(offer.toArray());

        return jdbc.batchUpdate("insert into offers(username, text) values (:username, :text)", params);

    }

    public boolean create(Offer offer) {

        BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(offer);

        return jdbc.update("insert into offers(username, text) values (:username, :text)", params) == 1;

    }

    public boolean update(Offer offer) {

        BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(offer);

        return jdbc.update("update offers set text=:text where id =:id", params) == 1;

    }

    public boolean delete(int id) {

        MapSqlParameterSource params = new MapSqlParameterSource("id", id);

        return jdbc.update("delete from offers where id=:id", params) == 1;

    }

    public Offer getOffer(int id) {

        MapSqlParameterSource params = new MapSqlParameterSource();

        params.addValue("id", id);

        return jdbc.queryForObject("select * from offers, users where offers.username=users.username and users.enabled=true and id=:id", params, new OfferRowMapper());

    }
}
