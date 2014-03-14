package aws;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import play.Configuration;

/**
 * Created on IntelliJ
 * User: Jef Waumans for Virtual Sushi
 * Date: 28/02/14
 * Time: 15:39
 */
public class DynamoFactory {

	private static AmazonDynamoDBClient client;

	private static DynamoDBMapper mapper;

	public static DynamoDBMapper getMapper() {
		if (mapper != null) return mapper;
		else initDynamo();
		return mapper;
	}

	public static AmazonDynamoDBClient getClient() {
		if (client != null) return client;
		else initDynamo();
		return client;
	}

	private static void initDynamo() {
		Configuration aws = Configuration.root().getConfig("aws");
		Configuration dynamodb = Configuration.root().getConfig("dynamodb");
		if (aws != null && dynamodb != null) {
			String accesskey = aws.getString("accesskey");
			String secretkey = aws.getString("secretkey");
			String endpoint = dynamodb.getString("endpoint");
			if (accesskey != null && secretkey != null && endpoint != null) {
				AWSCredentials credentials = new BasicAWSCredentials(accesskey, secretkey);
				client = new AmazonDynamoDBClient(credentials);
				client.setEndpoint(endpoint);
				mapper = new DynamoDBMapper(client);
			}
		}
	}


	}
