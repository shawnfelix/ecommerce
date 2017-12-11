package cse305.model.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import cse305.model.entities.Review;

public class ReviewRowMapper implements RowMapper<Review> {

	@Override
	public Review mapRow(ResultSet rs, int rowNum) throws SQLException {
		Review review = new Review();
		
		review.setCustomerId(rs.getInt("customer_id"));
		review.setItemId(rs.getInt("item_id"));
		review.setReviewId(rs.getInt("review_id"));
		review.setReviewDetails(rs.getString("review_details"));
		
		return review;
	}

	
	
}
