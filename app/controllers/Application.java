package controllers;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedScanList;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import models.Tweet;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.node.ObjectNode;
import play.*;
import play.libs.Json;
import play.modules.aws.dynamodb.DynamoDB;
import play.mvc.*;

import views.html.*;
import views.html.index;

import java.util.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class Application extends Controller {

	public static Result index() {
		return ok(index.render("Your new application is ready."));
	}

	@BodyParser.Of(BodyParser.Json.class)
	public static Result ticks(String aws_key) {
		List<Tweet> tweets = getTweets(aws_key);
		if (tweets == null) {
			return badRequest("Missing parameter [name]");
		} else {
			ObjectNode result = Json.newObject();
			ObjectMapper mapper = new ObjectMapper();
			JsonNode tweetsNode = mapper.valueToTree(tweets);
			result.put("status", "OK");
			result.put("tweets", tweetsNode);
			return ok(result);
		}
	}


	/*public static List<Tweet> getTweets() {
		DynamoDBMapper dynamoMapper = DynamoDB.mapper();
		DynamoDBQueryExpression queryExpression;
		queryExpression = new DynamoDBQueryExpression(
				new AttributeValue().withS(getTweetKey()));

		List<Tweet> tweets;
		tweets = dynamoMapper.query(Tweet.class, queryExpression);
		if (tweets == null || tweets.isEmpty()) {
			tweets = getMostRecentTweets();
		}
		return tweets;
	}*/



	public static List<Tweet> getTweets(String aws_key) {
		DynamoDBMapper dynamoMapper = DynamoDB.mapper();
		Tweet q = new Tweet();
		q.setId(aws_key);

		DynamoDBQueryExpression queryExpression = new DynamoDBQueryExpression().withHashKeyValues(q);

		List<Tweet> tweets;
		tweets = dynamoMapper.query(Tweet.class, queryExpression);
		if (tweets == null || tweets.isEmpty()) {
			tweets = getMostRecentTweets();
		}
		return tweets;
	}


	private static List<Tweet> getMostRecentTweets() {
		DynamoDBMapper dynamoMapper = DynamoDB.mapper();
		DynamoDBScanExpression dynamoDBScanExpression = new DynamoDBScanExpression();
		PaginatedScanList<Tweet> psl = dynamoMapper.scan(Tweet.class, dynamoDBScanExpression);
		Tweet[] tweets = psl.toArray(new Tweet[0]);
		Arrays.sort(tweets, new Comparator<Tweet>() {
			@Override
			public int compare(Tweet t1, Tweet t2) {
				return t2.getId().compareToIgnoreCase(t1.getId());
			}
		});
		List<Tweet> sorted = new ArrayList<Tweet>();
		for (int i = 0; i < 5; i++) {
			sorted.add(tweets[i]);
		}
		return sorted;
	}

	private static String getTweetKey() {
		DateFormat sdf = new SimpleDateFormat("yyyy_MM_dd_HH_");
		Date now = new Date();
		Calendar cal = new GregorianCalendar();
		cal.setTime(now);
		int minutes = cal.get(Calendar.MINUTE);
		String minute_key = "Q" + (1 + minutes / 15);
		String key = sdf.format(new Date());
		return key + minute_key;
	}

	private static String getTestKey() {
		return "2012_12_18_09_Q3";
	}

}
