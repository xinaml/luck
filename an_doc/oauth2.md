
    /oauth/authorize:授权端点

    /oauth/token:令牌端点
    
    /oauth/confirm_access:用户在此处批准授权
    
    /oauth/error: 用于在授权服务器中渲染错误
    
    /oauth/check_token: 由资源服务器用来解码访问令牌
    
    /oauth/token_key: 如果使用JWT令牌，公开密钥用于令牌验证
    
    
    密码模式获取token:
    post: localhost:8083/oauth/token
    
    grant_type:password
    client_id:webApp
    client_secret:123456
    username:lgq
    password:123456
    
    返回：
        {
            "access_token": "38b3d8ed-487b-4fbc-a871-1fd86bc502c8",
            "token_type": "bearer",
            "refresh_token": "910afbec-3916-4bc3-825f-0fb8e7abd13e",
            "expires_in": 43074,
            "scope": "webApp"
        }
