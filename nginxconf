 server {
        client_max_body_size 1G;

        listen       80;
        server_name  localhost;

        #charset koi8-r;

        #access_log  logs/host.access.log  main;

        location / {
                proxy_set_header X-Forwarded-Host $host;
                proxy_set_header X-Forwarded-Server $host;
                proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;
                proxy_pass http://127.0.0.1:9002/;
        }

        location /resources/ {
                alias /usr/local/tomcat8/apache-tomcat-8.0.36/webapps/ROOT/resources/;
                allow all;
        }

        location /resources/images/uploadPicture/ {
                alias /home/sqihome/sqihomepage/boardFiles/;
                allow all;
        }

        location /resources/images/uploadPicture/resume/ {
                alias /home/sqihome/sqihomepage/resumeImg/;
                allow all;
        }

        location /uploadFiles/ {
                alias  /home/sqihome/newhomepage/uploadData/board/;
                allow all;
        }

        #location / {
        #    root   html;
        #    index  index.html index.htm;
        #}

        #error_page  404              /404.html;

        # redirect server error pages to the static page /50x.html
        #
        error_page   500 502 503 504  /50x.html;
        location = /50x.html {
            root   html;
        }

        # proxy the PHP scripts to Apache listening on 127.0.0.1:80
        #
        #location ~ \.php$ {
        #    proxy_pass   http://127.0.0.1;
        #}

        # pass the PHP scripts to FastCGI server listening on 127.0.0.1:9000
        #
        #location ~ \.php$ {
        #    root           html;
        #    fastcgi_pass   127.0.0.1:9000;
        #    fastcgi_index  index.php;
        #    fastcgi_param  SCRIPT_FILENAME  /scripts$fastcgi_script_name;
        #    include        fastcgi_params;
        #}

        # deny access to .htaccess files, if Apache's document root
        # concurs with nginx's one
        #
        #location ~ /\.ht {
        #    deny  all;
        #}
    }
	
	
	
	
	docker build -t custom-nginx .
