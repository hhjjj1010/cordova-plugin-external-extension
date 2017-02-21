/********* externalExtension.m Cordova Plugin Implementation *******/

#import <Cordova/CDV.h>

@interface externalExtension : CDVPlugin {
  // Member variables go here.
}

- (void)openMobileQQChat:(CDVInvokedUrlCommand*)command;

- (void)openURL:(CDVInvokedUrlCommand*)command;

@end

@implementation externalExtension

- (void)openMobileQQChat:(CDVInvokedUrlCommand*)command
{
    NSString* qqNumber = [command.arguments objectAtIndex:0];

    if ([[UIApplication sharedApplication] canOpenURL:[NSURL URLWithString:@"mqq://"]]) {
         NSString *urlStr=[NSString stringWithFormat:@"mqq://im/chat?chat_type=wpa&uin=%@&version=1&src_type=web",qqNumber];
         NSURL *url = [NSURL URLWithString:urlStr];
         [[UIApplication sharedApplication] openURL:url];
    } else {
        UIAlertView *alert = [[UIAlertView alloc] initWithTitle:@"提示" message:@"您还没有安装QQ" delegate:nil cancelButtonTitle:(NSString *)@"知道了" otherButtonTitles:nil];
        [alert show];
    }

}

- (void)openURL:(CDVInvokedUrlCommand*)command
{
    NSString *urlStr = [command.arguments objectAtIndex:0];
    if (urlStr && urlStr.length > 0)
    {
        NSURL *url = [NSURL URLWithString:urlStr];
        [[UIApplication sharedApplication] openURL:url];
    }
    else
    {
        UIAlertView *alert = [[UIAlertView alloc] initWithTitle:@"提示" message:@"URL不能为空！" delegate:nil cancelButtonTitle:(NSString *)@"知道了" otherButtonTitles:nil];
        [alert show];
    }
}



@end
