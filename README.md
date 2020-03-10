# miaosha
秒杀小项目-来源为慕课网
## 遇到的小问题
因为主键使用自增，所以在mapper.xml文件中的insert和insertSelective需要配置
useGeneratedKeys="true" keyProperty="id"，不要就要出错
## 遇到insertSelective出错
将这句话使用try catch包起来，调试一下，打印出错误信息，得知在数据库定义的时候，将item的主键未设置成自增，还傻傻的加了一个默认值，
改为自增并取消默认值后，一切正常
