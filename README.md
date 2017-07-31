# Develop a Cassandra based application on Oracle Application Container Cloud

Check [the blog](https://medium.com/@abhishek1987/develop-deploy-a-cassandra-based-application-on-oracle-cloud-ca3d9d124bf6) for details

## Build

- `git clone https://github.com/abhirockzz/accs-cassandra.git`
- `mvn clean install` - creates `accs-compute-cassandra-dist.zip` in `target` directory

## Deploy to Oracle Cloud

- Use Developer Cloud - read [the blog](tbd)
- Use Application Container Cloud [console](http://docs.oracle.com/en/cloud/paas/app-container-cloud/csjse/exploring-application-deployments-page.html#GUID-5E4472B1-F5C6-4556-908C-D76C4C14FC60)
- Use Application Container Cloud [REST APIs](http://docs.oracle.com/en/cloud/paas/app-container-cloud/apcsr/op-paas-service-apaas-api-v1.1-apps-%7BidentityDomainId%7D-post.html)
- Use Application Container Cloud [PSM APIs](https://docs.oracle.com/en/cloud/paas/java-cloud/pscli/accs-push.html)

## Run locally

- Start Cassandra instance
- Set the following environment variables
	- `CASSANDRA_HOST`
	- `CASSANDRA_PORT`
	- `CASSANDRA_USERNAME`
	- `CASSANDRA_PASSWORD`
- `java -jar target/accs-compute-cassandra.jar`

## Test

Refer **Test the application** section in [the blog](https://medium.com/@abhishek1987/develop-deploy-a-cassandra-based-application-on-oracle-cloud-ca3d9d124bf6)
