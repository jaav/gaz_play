package models;

import aws.DynamoDBModel;
import aws.DynamoFactory;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

import java.util.Set;

@DynamoDBTable(tableName = "sponsor")
public class Sponsor extends DynamoDBModel implements Comparable<Sponsor> {

	private String id;
	private String name;
	private String tweet;
	private String tweep;
	private String url;

	@DynamoDBHashKey(attributeName = "id")
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}


	@DynamoDBAttribute(attributeName = "name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	@DynamoDBAttribute(attributeName = "tweet")
	public String getTweet() {
		return tweet;
	}

	public void setTweet(String tweet) {
		this.tweet = tweet;
	}


	@DynamoDBAttribute(attributeName = "tweep")
	public String getTweep() {
		return tweep;
	}

	public void setTweep(String tweep) {
		this.tweep = tweep;
	}


	@DynamoDBAttribute(attributeName = "url")
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int compareTo(Sponsor sponsor) {
		if (this.id != null && sponsor.getId() != null) {
			return this.id.compareToIgnoreCase(sponsor.getId());
		}
		return 0;
	}
}