package models;

import aws.DynamoDBModel;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

@DynamoDBTable(tableName = "gsw_social")
public class GswSocial extends DynamoDBModel implements Comparable<GswSocial> {

	private Long id;
	private String email;
	private String language;
	private String comment;

	@DynamoDBHashKey(attributeName = "id")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	                    
	@DynamoDBAttribute(attributeName = "email")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@DynamoDBAttribute(attributeName = "language")
	public String getLanguage() {
		return language;
	}

	public void setLanguage(String tweet) {
		this.language = language;
	}


	@DynamoDBAttribute(attributeName = "comment")
	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public int compareTo(GswSocial gswSocial) {
		if (this.id != null && gswSocial.getId() != null) {
			return (int)(this.id - gswSocial.getId());
		}
		return 0;
	}
}