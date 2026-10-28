package net.wanho.service;

import java.util.Map;

public interface VodCallbackServiceI {

    void handleCallback(Map<String, Object> payload);
}
