package models;

import aws.DynamoDBModel;
import aws.DynamoFactory;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

import java.util.Set;

@DynamoDBTable(tableName = "tick_message")
public class Sponsor extends DynamoDBModel implements Comparable<Sponsor> {

	private Long id;
	private String name;
	private String tweet;
	private String tweep;
	private String url;
	private String target;

	@DynamoDBHashKey(attributeName = "id")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	@DynamoDBRangeKey(attributeName = "target")
	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
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
			return (int)(this.id - sponsor.getId());
		}
		return 0;
	}
}