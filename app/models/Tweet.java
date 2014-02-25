package models;

import com.amazonaws.services.dynamodbv2.datamodeling.*;
import play.modules.aws.dynamodb.DynamoDB;
import play.modules.aws.dynamodb.DynamoDBModel;

import java.util.List;
import java.util.Set;

@DynamoDBTable(tableName = "nuuus")
public class Tweet extends DynamoDBModel implements Comparable<Tweet> {

	private String id;
	private Integer sequence;
	private Long lastModified;
	private Integer score;
	private Integer rawRate;
	private String tweet;
	private String author;
	private Set<String> hashtags;
	private Set<String> urls;
	private String image;
	private String imageExtension;
	private String style;

	@DynamoDBHashKey(attributeName = "id")
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@DynamoDBRangeKey(attributeName = "sequence")
	public Integer getSequence() {
		return sequence;
	}

	public void setSequence(Integer sequence) {
		this.sequence = sequence;
	}

	@DynamoDBAttribute(attributeName = "tweet")
	public String getTweet() {
		return tweet;
	}

	public void setTweet(String tweet) {
		this.tweet = tweet;
	}

	@DynamoDBAttribute(attributeName = "hashtags")
	public Set<String> getHashtags() {
		return hashtags;
	}

	public void setHashtags(Set<String> hashtags) {
		this.hashtags = hashtags;
	}

	@DynamoDBAttribute(attributeName = "author")
	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}


	@DynamoDBAttribute(attributeName = "score")
	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}


	@DynamoDBAttribute(attributeName = "urls")
	public Set<String> getUrls() {
		return urls;
	}

	public void setUrls(Set<String> urls) {
		this.urls = urls;
	}

	@DynamoDBAttribute(attributeName = "imageExtension")
	public String getImageExtension() {
		return imageExtension;
	}

	public void setImageExtension(String imageExtension) {
		this.imageExtension = imageExtension;
	}

	@DynamoDBAttribute(attributeName = "style")
	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
	}

    /*public static Tweet authenticate(String email, String password) {
        Tweet user = DynamoDB.mapper().load(Tweet.class, email);
        if (user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }*/

	public static Tweet create(String tweetContent) {
		Tweet tweet = new Tweet();
		tweet.setTweet(tweetContent);
		tweet.save();
		return tweet;
	}

	public static boolean exists(String email) {
		Tweet tweet = DynamoDB.mapper().load(Tweet.class, email);
		return tweet != null;
	}

	public int compareTo(Tweet tweet) {
		if (this.id != null && tweet.getId() != null) {
			return this.id.compareToIgnoreCase(tweet.getId());
		}
		return 0;
	}

}