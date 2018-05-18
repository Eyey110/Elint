# Elint
### TODO

一些常用的可自定义Lint规则。

# 如何使用
clone该工程，rebuild会在build/outputs下生成一个aar文件。
在需要检测的工程中import一个module,从aar导入module，并在需要检测的工程目录下新建“lint-rules.json”文件，直接配置规则即可。

示例配置在[这里]


# 目前支持的配置:
- Api非法调用
- 直接调用构造函数非法
- 资源命名规则正则匹配


