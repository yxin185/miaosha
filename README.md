# miaosha
秒杀小项目-来源为慕课网
## 遇到的小问题
因为主键使用自增，所以在mapper.xml文件中的insert和insertSelective需要配置
useGeneratedKeys="true" keyProperty="id"，不要就要出错
## 遇到insertSelective出错
将这句话使用try catch包起来，调试一下，打印出错误信息，得知在数据库定义的时候，将item的主键未设置成自增，还傻傻的加了一个默认值，
改为自增并取消默认值后，一切正常
## 每次通过mybatis生成mapper的xml文件
好像每次生成的文件都可能覆盖掉以前写的，所以出现错误
记得debug一下，看一下是哪里的问题
## 在用户减库存那个方法对应的ItemStockDOMapper.xml
因为自己更改decreaseStock方法的update语句时，多写了一个;，导致
频繁报错，通过try catch 减库存的语句，打印出错误信息，找到
错误的原因，然后修正就可以了
