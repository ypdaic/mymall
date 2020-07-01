local isExists = redis.call("exists", KEYS[1])
if (isExists ~= 0) then
    local oldToken = redis.call("get", KEYS[1]);
    local currentToken = ARGV[1];
    if (oldToken == currentToken) then
        redis.call("del", KEYS[1])
        return true;
    end;
end;
return false;