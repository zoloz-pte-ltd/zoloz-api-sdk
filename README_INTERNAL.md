# 内部说明
zoloz api代码仓库，现已经和开源github地址
https://github.com/zoloz-pte-ltd/zoloz-api-sdk
以及开源仓库sonatype集成

流水线会在合并master后触发，会自动同步最新的仓库中的代码以及对应的git history.
因此开源仓库的任何代码同步到开源仓库的master都**必须通过流水线同步，不允许手动同步**

另外同时也有push_to_gitlab和deploy_to_sonatype两个手动触发的aci，方便特殊情况下自助发布用，例如发布一个release版本等。

开源发布会去掉 aci文件，maven settings文件, pgp秘钥文件 以及这个内部readme
如果只是修改这些文件同步会失败，但是不影响，忽略就好。

流水线详情请自行阅读aci代码

ref:
https://yuque.antfin.com/zoloz/server/hl86nl
https://www.gnupg.org/gph/en/manual/c14.html

一个坑：
加签需要使用到passphrase，但是传passphrase的方法根据gpg的版本会产生区别
本地brew install的gpg版本支持--pinentrymode loopback的模式传递
但是aci镜像中的gpg虽然也是2.x版本 但是不支持 要用旧的--batch命令传递
所以本地和流水线机器运行的命令是不一样的

自己本地发布的时候，只需要引入pgp秘钥文件 然后mvn clean deploy 手动输入passphrase就好了，就不用传参了。