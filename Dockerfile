FROM jboss/wildfly:latest
MAINTAINER "agpaluch"
ADD webapp.war /opt/jboss/wildfly/standalone/deployments/
CMD ["/opt/jboss/wildfly/bin/standalone.sh", "-b", "0.0.0.0"]

