<?xml version="1.0" encoding="UTF-8"?>
<Server port="8007" shutdown="SHUTDOWN">
        <Listener className="org.apache.catalina.startup.VersionLoggerListener"/>
        <Listener SSLEngine="on" className="org.apache.catalina.core.AprLifecycleListener"/>
        <Listener className="org.apache.catalina.core.JreMemoryLeakPreventionListener"/>
        <Listener className="org.apache.catalina.mbeans.GlobalResourcesLifecycleListener"/>
        <Listener className="org.apache.catalina.core.ThreadLocalLeakPreventionListener"/>
        <GlobalNamingResources>
                <Resource auth="Container" description="User database that can be updated and saved" factory="org.apache.catalina.users.MemoryUserDatabaseFactory" name="UserDatabase" pathname="conf/tomcat-users.xml" type="org.apache.catalina.UserDatabase"/>
        </GlobalNamingResources>

        <Service name="Catalina">
                <Connector connectionTimeout="20000" port="8082" protocol="HTTP/1.1" redirectPort="8443"/>
                <Connector connectionTimeout="60000" port="443" protocol="org.apache.coyote.http11.Http11NioProtocol"
                        maxThreads="150" SSLEnabled="true" scheme="https" secure="true" keystoreFile="keystore경로" keystorePass="keystore패스워드"
                        clientAuth="false" sslProtocol="TLS" sslEnabledProtocols="TLSv1.2" ciphers="TLS_ECDHE_RSA_WITH_AES_256_GCM_SHA384"/>

                <Connector port="8011" protocol="AJP/1.3" redirectPort="8443"/>

                <Engine defaultHost="localhost" name="Catalina">
                        <Realm className="org.apache.catalina.realm.LockOutRealm">
                        <Realm className="org.apache.catalina.realm.UserDatabaseRealm" resourceName="UserDatabase"/>
                        </Realm>

                        <Host appBase="WebContent" autoDeploy="true" name="localhost" unpackWARs="true">
                                <Valve className="org.apache.catalina.valves.AccessLogValve" directory="logs" pattern="%h %l %u %t &quot;%r&quot; %s %b" prefix="localhost_access_log" suffix=".txt"/>
                                <Context docBase="프로젝트 경로" path="" reloadable="true">
                                        <Manager className="org.apache.catalina.session.PersistentManager" saveOnRestart="false" />
                                        <Resource type="javax.sql.DataSource" name="jdbc/ssbrdb" auth="Container" username="root" url="jdbc:mysql://localhost:13306/ssbr?useUnicode=true&amp;characterEncoding=utf8" password="system" maxWait="-1" maxIdle="10" maxActive="20" driverClassName="com.mysql.jdbc.Driver"/>
                                </Context>
                        </Host>
                </Engine>
        </Service>
</Server>
