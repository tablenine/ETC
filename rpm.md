1. rpm 빌드 시 name만 다르면 다른 프로그램이다.
2. 설치하는 파일이 같으면 에러 발생

### 빌드파일

``` sh
#!/bin/sh
name=tomcat-4.1.0.1
nameInt=~/rpmbuild/SOURCES/${name}_int
nameExt=~/rpmbuild/SOURCES/${name}_ext

cd /www/tomcat
if [ -d $nameInt ]; then rm -r $nameInt; fi
if [ -d $nameExt ]; then rm -r $nameExt; fi

/bin/cp -r tomcat_int ~/rpmbuild/SOURCES/${name}_int &&
/bin/cp -r tomcat_ext ~/rpmbuild/SOURCES/${name}_ext &&
cd /www/tomcat/rpm/ &&
rpmbuild -ba tomcat.spec
```
### spec 파일

``` sh
%define name tomcat-4.1.0.1
%define rel el6
Summary: tomcat
Name: %{name}
Version: 8.0.46
Release: %{rel}
License: boan
Source0: %{name}_int
Source1: %{name}_ext
BuildRoot: /var/tmp/tomcat-buildroot
%description
tomcat

%prep

%install
if [ -d $RPM_BUILD_ROOT ]; then rm -r $RPM_BUILD_ROOT; fi

mkdir -p $RPM_BUILD_ROOT
mkdir -p $RPM_BUILD_ROOT/opt/ssbr/3rdparty/
cp -rp $RPM_SOURCE_DIR/%{name}_int $RPM_BUILD_ROOT/opt/ssbr/3rdparty/%{name}_int &&
cp -rp $RPM_SOURCE_DIR/%{name}_ext $RPM_BUILD_ROOT/opt/ssbr/3rdparty/%{name}_ext

%clean

%files
/opt/ssbr/3rdparty/%{name}_int
/opt/ssbr/3rdparty/%{name}_ext

%post
mkdir -p /opt/ssbr/3rdparty
ln -sfn /opt/ssbr/3rdparty/%{name}_int /opt/ssbr/3rdparty/Int
ln -sfn /opt/ssbr/3rdparty/%{name}_ext /opt/ssbr/3rdparty/Ext

chmod 755 /opt/ssbr/3rdparty/Int/bin/*
chmod 755 /opt/ssbr/3rdparty/Ext/bin/*

%postun
service deamon stop
unlink /opt/ssbr/3rdparty/Int
unlink /opt/ssbr/3rdparty/Ext
rm -rf /opt/ssbr/3rdparty/%{name}_int
rm -rf /opt/ssbr/3rdparty/%{name}_ext
```

