# PaytmLabs SDE Challenge

## Coding Question

Write an interface for a data structure that can provide the moving average of the last N elements added, add elements to the structure and get access to the elements. Provide an efficient implementation of the interface for the data structure.

### Minimum Requirements

1. Provide a separate interface (IE `interface`/`trait`) with documentation for the data structure
2. Provide an implementation for the interface
3. Provide any additional explanation about the interface and implementation in a README file.

### Solution
My implementation uses an ArrayList as he basis for the data structure. On initialization, an N value must be provided. However, this value can be updated, and the moving average will be recomputed. 

The solution does a scan of the last N valid elements on the first call. On subsequent calls, no scan needs to be done as the results from the previous search are saved & the sliding window technique is used, resulting in a constant time update of the moving average. 

If the N is larger than the number of elements in the store, no moving average will be returned, since otherwise the results could be misleading.

## Design Question

Design A Google Analytic like Backend System.
We need to provide Google Analytic like services to our customers. Please provide a high level solution design for the backend system. Feel free to choose any open source tools as you want.

### Requirements

1. Handle large write volume: Billions of write events per day.
2. Handle large read/query volume: Millions of merchants wish to gain insight into their business. Read/Query patterns are time-series related metrics.
3. Provide metrics to customers with at most one hour delay.
4. Run with minimum downtime.
5. Have the ability to reprocess historical data in case of bugs in the processing logic.

### Solution
These requirements fit very well with the generalized use case for a product like Amazon Kinesis Firehose: https://aws.amazon.com/kinesis/data-firehose/ 

- Client applications (e.g. browser apps) can send events to an SQS queue. 
- An AWS Lambda function will read off this queue, and validate the event data. Then it will put data to a Amazon Kinesis Data Stream. 
- The raw data can then be stored in S3 in case it needs to be reprocessed.
- Kinesis Data Analytics can be used to run SQL like reports on the events in the stream live. 
- These queries can either be used directly by the client, or we can create JSON payloads stored in S3 for them. Since this is timeseries data, we can segregate these payloads by time if needed.
- We can also use Apache Spark to help query these payloads partitioned by time.
- A webserver running on EC2 can accept request from browser or mobile apps, submit processing jobs to Spark, and then return results to the client, in order to make the analytics data query-able.
- This can run with minimal downtime as AWS has SLA guarantees for availability and durability. 

Please see attached diagram in `diagram.jpg`