package com.chwltd.api;

/*
*
* 最后修改日期：2024-09-21
*
* */

import com.chwltd.utils.OkHttpUtils;
import com.google.gson.JsonObject;

import java.util.Arrays;
import java.util.List;

public class ApiUtils {
    /*
     *
     * APP相关
     *
     * */

    //获取APP信息
    public static String getAppInfo() {
        try {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("appid", ApiConfig.getAppid());
            jsonObject.addProperty("timestamp", System.currentTimeMillis());

            return SignUtils.decrypt(OkHttpUtils.postJson(ApiConfig.getApi() + "get_app_info", jsonObject.toString()));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //获取APP更新记录
    public static String getAppUpdateRecords(int limit, int page) {
        try {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("appid", ApiConfig.getAppid());
            jsonObject.addProperty("limit", limit);
            jsonObject.addProperty("page", page);
            jsonObject.addProperty("timestamp", System.currentTimeMillis());

            return SignUtils.decrypt(OkHttpUtils.postJson(ApiConfig.getApi() + "get_app_update_records", jsonObject.toString()));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //增加APP访问量
    public static String addView() {
        try {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("appid", ApiConfig.getAppid());
            jsonObject.addProperty("timestamp", System.currentTimeMillis());

            return SignUtils.decrypt(OkHttpUtils.postJson(ApiConfig.getApi() + "add_view", jsonObject.toString()));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //获取APP相关统计数据
    public static String getAppStatisticalData() {
        try {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("appid", ApiConfig.getAppid());
            jsonObject.addProperty("timestamp", System.currentTimeMillis());

            return SignUtils.decrypt(OkHttpUtils.postJson(ApiConfig.getApi() + "get_app_statistical_data", jsonObject.toString()));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //利用appkey 增减 用户金币或积分 会员天数
    public static String adminIncreaseDecrease(String appkey, String userid) {
        try {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("appid", ApiConfig.getAppid());
            jsonObject.addProperty("appkey", appkey);
            jsonObject.addProperty("userid", userid);
            jsonObject.addProperty("timestamp", System.currentTimeMillis());

            return SignUtils.decrypt(OkHttpUtils.postJson(ApiConfig.getApi() + "admin_increase_decrease", jsonObject.toString()));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /*
     *
     * 用户相关
     *
     * */

    //用户登录(无验证码)
    public static String login(String username, String password) {
        try {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("appid", ApiConfig.getAppid());
            jsonObject.addProperty("username", username);
            jsonObject.addProperty("password", password);
            jsonObject.addProperty("device", ApiConfig.getDevice());

            jsonObject.addProperty("timestamp", System.currentTimeMillis());

            return SignUtils.decrypt(OkHttpUtils.postJson(ApiConfig.getApi() + "login", jsonObject.toString()));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //用户登录(携带验证码)
    public static String login(String username, String password, String captcha) {
        try {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("appid", ApiConfig.getAppid());
            jsonObject.addProperty("username", username);
            jsonObject.addProperty("password", password);
            jsonObject.addProperty("device", ApiConfig.getDevice());
            jsonObject.addProperty("captcha", captcha);
            jsonObject.addProperty("timestamp", System.currentTimeMillis());

            return SignUtils.decrypt(OkHttpUtils.postJson(ApiConfig.getApi() + "login", jsonObject.toString()));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //登录图片验证码(1:登录，2:注册)
    public static String getImgageVerificationCode(int type) {
        try {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("appid", ApiConfig.getAppid());
            jsonObject.addProperty("type", type);
            jsonObject.addProperty("timestamp", System.currentTimeMillis());

            return SignUtils.decrypt(OkHttpUtils.postJson(ApiConfig.getApi() + "get_image_verification_code", jsonObject.toString()));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //手机号登录
    public static String mobileLogin(String mobile, String code, String captcha) {
        try {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("appid", ApiConfig.getAppid());
            jsonObject.addProperty("mobile", mobile);
            jsonObject.addProperty("code", code);
            jsonObject.addProperty("device", ApiConfig.getDevice());
            jsonObject.addProperty("captcha", captcha);
            jsonObject.addProperty("timestamp", System.currentTimeMillis());

            return SignUtils.decrypt(OkHttpUtils.postJson(ApiConfig.getApi() + "mobile_login", jsonObject.toString()));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //获取短信验证码
    public static String getMobileVerificationCode(String mobile, int type) {
        try {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("appid", ApiConfig.getAppid());
            jsonObject.addProperty("mobile", mobile);
            jsonObject.addProperty("type", type);
            jsonObject.addProperty("timestamp", System.currentTimeMillis());

            return SignUtils.decrypt(OkHttpUtils.postJson(ApiConfig.getApi() + "get_mobile_verification_code", jsonObject.toString()));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //用户注册（1：手机号注册，2：邮箱注册）
    public static String register(String username, String password, String data, String captcha, String invitecode, int type) {
        try {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("appid", ApiConfig.getAppid());
            jsonObject.addProperty("username", username);
            jsonObject.addProperty("password", password);
            if (type == 1) {
                jsonObject.addProperty("mobile", data);
            } else {
                jsonObject.addProperty("email", data);
            }
            jsonObject.addProperty("device", ApiConfig.getDevice());
            jsonObject.addProperty("captcha", captcha);
            jsonObject.addProperty("invitecode", invitecode);
            jsonObject.addProperty("timestamp", System.currentTimeMillis());

            return SignUtils.decrypt(OkHttpUtils.postJson(ApiConfig.getApi() + "register", jsonObject.toString()));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //获取邮箱验证码（1：注册，2：找回密码，3：修改邮箱）
    public static String getEmailVerificationCode(String email, int type) {
        try {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("appid", ApiConfig.getAppid());
            jsonObject.addProperty("email", email);
            jsonObject.addProperty("type", type);
            jsonObject.addProperty("timestamp", System.currentTimeMillis());

            return SignUtils.decrypt(OkHttpUtils.postJson(ApiConfig.getApi() + "get_email_verification_code", jsonObject.toString()));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //用户心跳
    public static String userHeartbeat() {
        try {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("appid", ApiConfig.getAppid());
            jsonObject.addProperty("usertoken", ApiConfig.getUserToken());
            jsonObject.addProperty("timestamp", System.currentTimeMillis());

            return SignUtils.decrypt(OkHttpUtils.postJson(ApiConfig.getApi() + "user_heartbeat", jsonObject.toString()));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //用户心跳
    public static String logOutLogin() {
        try {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("appid", ApiConfig.getAppid());
            jsonObject.addProperty("usertoken", ApiConfig.getUserToken());
            jsonObject.addProperty("timestamp", System.currentTimeMillis());

            return SignUtils.decrypt(OkHttpUtils.postJson(ApiConfig.getApi() + "log_out_login", jsonObject.toString()));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //获取用户信息
    public static String getUserInformation(String userid) {
        try {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("appid", ApiConfig.getAppid());
            jsonObject.addProperty("userid", userid);
            jsonObject.addProperty("usertoken", ApiConfig.getUserToken());
            jsonObject.addProperty("timestamp", System.currentTimeMillis());

            return SignUtils.decrypt(OkHttpUtils.postJson(ApiConfig.getApi() + "get_user_information", jsonObject.toString()));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //获取登录用户的全部信息
    public static String getUserOtherInformation() {
        try {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("appid", ApiConfig.getAppid());
            jsonObject.addProperty("usertoken", ApiConfig.getUserToken());
            jsonObject.addProperty("timestamp", System.currentTimeMillis());

            return SignUtils.decrypt(OkHttpUtils.postJson(ApiConfig.getApi() + "get_user_other_information", jsonObject.toString()));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //用户签到
    public static String userSignIn() {
        try {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("appid", ApiConfig.getAppid());
            jsonObject.addProperty("usertoken", ApiConfig.getUserToken());
            jsonObject.addProperty("timestamp", System.currentTimeMillis());

            return SignUtils.decrypt(OkHttpUtils.postJson(ApiConfig.getApi() + "user_sign_in", jsonObject.toString()));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //找回密码
    public static String retrievePassword(String username, String password, String captcha, int type) {
        try {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("appid", ApiConfig.getAppid());
            jsonObject.addProperty("username", username);
            jsonObject.addProperty("password", password);
            jsonObject.addProperty("captcha", captcha);
            jsonObject.addProperty("type", type);
            jsonObject.addProperty("timestamp", System.currentTimeMillis());

            return SignUtils.decrypt(OkHttpUtils.postJson(ApiConfig.getApi() + "retrieve_password", jsonObject.toString()));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //上传头像
    public static String uploadAvatar(String path) {
        try {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("appid", ApiConfig.getAppid());
            jsonObject.addProperty("usertoken", ApiConfig.getUserToken());
            jsonObject.addProperty("timestamp", System.currentTimeMillis());

            List<String> list = Arrays.asList();
            list.add("file\n"+path);

            return SignUtils.decrypt(OkHttpUtils.uploadMultipleFilesWithJson(ApiConfig.getApi() + "retrieve_password", list, jsonObject.toString()));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //上传背景
    public static String uploadBackground(String path) {
        try {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("appid", ApiConfig.getAppid());
            jsonObject.addProperty("usertoken", ApiConfig.getUserToken());
            jsonObject.addProperty("timestamp", System.currentTimeMillis());

            List<String> list = Arrays.asList();
            list.add("file\n"+path);

            return SignUtils.decrypt(OkHttpUtils.uploadMultipleFilesWithJson(ApiConfig.getApi() + "upload_background", list, jsonObject.toString()));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //修改密码
    public static String changePassword(String password, String new_password) {
        try {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("appid", ApiConfig.getAppid());
            jsonObject.addProperty("usertoken", ApiConfig.getUserToken());
            jsonObject.addProperty("password", password);
            jsonObject.addProperty("new_password", new_password);
            jsonObject.addProperty("timestamp", System.currentTimeMillis());

            return SignUtils.decrypt(OkHttpUtils.postJson(ApiConfig.getApi() + "change_password", jsonObject.toString()));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //修改用户信息
    public static String modifyUserInformation(String nickname, String sex, String qq, String usertx, String userbg, String signature) {
        try {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("appid", ApiConfig.getAppid());
            jsonObject.addProperty("usertoken", ApiConfig.getUserToken());
            jsonObject.addProperty("nickname", nickname);
            jsonObject.addProperty("sex", sex);
            jsonObject.addProperty("qq", qq);
            jsonObject.addProperty("usertx", usertx);
            jsonObject.addProperty("userbg", userbg);
            jsonObject.addProperty("signature", signature);
            jsonObject.addProperty("timestamp", System.currentTimeMillis());

            return SignUtils.decrypt(OkHttpUtils.postJson(ApiConfig.getApi() + "modify_user_information", jsonObject.toString()));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //修改邮箱
    public static String modifyUserEmail(String email, String code) {
        try {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("appid", ApiConfig.getAppid());
            jsonObject.addProperty("usertoken", ApiConfig.getUserToken());
            jsonObject.addProperty("email", email);
            jsonObject.addProperty("code", code);
            jsonObject.addProperty("timestamp", System.currentTimeMillis());

            return SignUtils.decrypt(OkHttpUtils.postJson(ApiConfig.getApi() + "modify_user_email", jsonObject.toString()));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //修改手机号
    public static String modifyUserPhone(String phone, String code) {
        try {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("appid", ApiConfig.getAppid());
            jsonObject.addProperty("usertoken", ApiConfig.getUserToken());
            jsonObject.addProperty("phone", phone);
            jsonObject.addProperty("code", code);
            jsonObject.addProperty("timestamp", System.currentTimeMillis());

            return SignUtils.decrypt(OkHttpUtils.postJson(ApiConfig.getApi() + "modify_user_phone", jsonObject.toString()));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //填写邀请码
    public static String fillInvitationCode(String invitecode) {
        try {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("appid", ApiConfig.getAppid());
            jsonObject.addProperty("usertoken", ApiConfig.getUserToken());
            jsonObject.addProperty("invitecode", invitecode);
            jsonObject.addProperty("timestamp", System.currentTimeMillis());

            return SignUtils.decrypt(OkHttpUtils.postJson(ApiConfig.getApi() + "fill_invitation_code", jsonObject.toString()));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //经验排行榜
    //sort:money为金币integral为积分exp经验
    //sortOrder:desc 倒叙 asc顺序 默认desc
    public static String rankingList(String sort, String sortOrder, String page, String limit) {
        try {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("appid", ApiConfig.getAppid());
            jsonObject.addProperty("sort", sort);
            jsonObject.addProperty("sortOrder", sortOrder);
            jsonObject.addProperty("page", page);
            jsonObject.addProperty("limit", limit);
            jsonObject.addProperty("timestamp", System.currentTimeMillis());

            return SignUtils.decrypt(OkHttpUtils.postJson(ApiConfig.getApi() + "modify_user_phone", jsonObject.toString()));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //邀请排行榜
    //sortOrder:desc 倒叙 asc顺序 默认desc
    public static String invitationRanking(String sortOrder, String page, String limit) {
        try {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("appid", ApiConfig.getAppid());
            jsonObject.addProperty("sortOrder", sortOrder);
            jsonObject.addProperty("page", page);
            jsonObject.addProperty("limit", limit);
            jsonObject.addProperty("timestamp", System.currentTimeMillis());

            return SignUtils.decrypt(OkHttpUtils.postJson(ApiConfig.getApi() + "invitation_ranking", jsonObject.toString()));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //关注/取消关注用户
    public static String followUsers(String followedid) {
        try {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("appid", ApiConfig.getAppid());
            jsonObject.addProperty("usertoken", ApiConfig.getUserToken());
            jsonObject.addProperty("followedid", followedid);
            jsonObject.addProperty("timestamp", System.currentTimeMillis());

            return SignUtils.decrypt(OkHttpUtils.postJson(ApiConfig.getApi() + "follow_users", jsonObject.toString()));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //查询关注列表
    public static String getFollowList(String page, String limit) {
        try {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("appid", ApiConfig.getAppid());
            jsonObject.addProperty("usertoken", ApiConfig.getUserToken());
            jsonObject.addProperty("page", page);
            jsonObject.addProperty("limit", limit);
            jsonObject.addProperty("timestamp", System.currentTimeMillis());

            return SignUtils.decrypt(OkHttpUtils.postJson(ApiConfig.getApi() + "get_follow_list", jsonObject.toString()));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //查询粉丝列表
    public static String getFanList(String page, String limit) {
        try {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("appid", ApiConfig.getAppid());
            jsonObject.addProperty("usertoken", ApiConfig.getUserToken());
            jsonObject.addProperty("page", page);
            jsonObject.addProperty("limit", limit);
            jsonObject.addProperty("timestamp", System.currentTimeMillis());

            return SignUtils.decrypt(OkHttpUtils.postJson(ApiConfig.getApi() + "get_fan_list", jsonObject.toString()));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //获取用户账单(纯账单)
    public static String getUserBilling(String page, String limit) {
        try {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("appid", ApiConfig.getAppid());
            jsonObject.addProperty("usertoken", ApiConfig.getUserToken());
            jsonObject.addProperty("page", page);
            jsonObject.addProperty("limit", limit);
            jsonObject.addProperty("timestamp", System.currentTimeMillis());

            return SignUtils.decrypt(OkHttpUtils.postJson(ApiConfig.getApi() + "get_fan_list", jsonObject.toString()));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //获取用户账单(纯账单)
    //transaction_type:交易类型交易类型 0邀请 1注册 2签到 3购买商品 4帖子付费购买 5附件的下载购买 6打赏文章 7提现 8卡密 9发帖 10评论 11点赞 12充值 13系统（appkey的增减）
    //type:0支出1收入
    //deduction_type:扣减类型0金币1积分
    public static String getUserBilling(String transaction_type, String type, String deduction_type, String page, String limit) {
        try {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("appid", ApiConfig.getAppid());
            jsonObject.addProperty("usertoken", ApiConfig.getUserToken());
            jsonObject.addProperty("transaction_type", transaction_type);
            jsonObject.addProperty("type", type);
            jsonObject.addProperty("deduction_type", deduction_type);
            jsonObject.addProperty("page", page);
            jsonObject.addProperty("limit", limit);
            jsonObject.addProperty("timestamp", System.currentTimeMillis());

            return SignUtils.decrypt(OkHttpUtils.postJson(ApiConfig.getApi() + "get_user_billing", jsonObject.toString()));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //QQ登录
    //openid:qq互联登录授权后返回的用户openid
    //access_token:qq互联登录授权后返回的access_token
    //qq_appid:QQ互联申请的APPID
    //state:0关闭同步QQ资料1开启同步QQ资料 默认0 注册时生效
    public static String qqLogin(String openid, String access_token, String state) {
        try {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("appid", ApiConfig.getAppid());
            jsonObject.addProperty("openid", openid);
            jsonObject.addProperty("access_token", access_token);
            jsonObject.addProperty("qq_appid", ApiConfig.getAppidQQ());
            jsonObject.addProperty("device", ApiConfig.getDevice());
            jsonObject.addProperty("state", state);
            jsonObject.addProperty("timestamp", System.currentTimeMillis());

            return SignUtils.decrypt(OkHttpUtils.postJson(ApiConfig.getApi() + "qq_login", jsonObject.toString()));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //查询关注状态
    //返回数据0互相关注 1他关注了我 2我关注了他 3互相都没有关注
    public static String getFollowStatus(String followedid) {
        try {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("appid", ApiConfig.getAppid());
            jsonObject.addProperty("usertoken", ApiConfig.getUserToken());
            jsonObject.addProperty("followedid", followedid);
            jsonObject.addProperty("timestamp", System.currentTimeMillis());

            return SignUtils.decrypt(OkHttpUtils.postJson(ApiConfig.getApi() + "get_follow_status", jsonObject.toString()));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //QQ登录
    //type:提现类型 0金币 1积分
    public static String userWithdrawCash(String name, String account, String money,String type,String remarks) {
        try {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("appid", ApiConfig.getAppid());
            jsonObject.addProperty("usertoken", ApiConfig.getUserToken());
            jsonObject.addProperty("name", name);
            jsonObject.addProperty("account", account);
            jsonObject.addProperty("money", money);
            jsonObject.addProperty("type", type);
            jsonObject.addProperty("remarks", remarks);

            jsonObject.addProperty("timestamp", System.currentTimeMillis());

            return SignUtils.decrypt(OkHttpUtils.postJson(ApiConfig.getApi() + "user_withdraw_cash", jsonObject.toString()));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //绑定QQ
    public static String bindQq(String openid, String access_token, String state) {
        try {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("appid", ApiConfig.getAppid());
            jsonObject.addProperty("usertoken", ApiConfig.getUserToken());
            jsonObject.addProperty("openid", openid);
            jsonObject.addProperty("access_token", access_token);
            jsonObject.addProperty("qq_appid", ApiConfig.getAppidQQ());
            jsonObject.addProperty("state", state);
            jsonObject.addProperty("timestamp", System.currentTimeMillis());

            return SignUtils.decrypt(OkHttpUtils.postJson(ApiConfig.getApi() + "bind_qq", jsonObject.toString()));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //获取用户提现记录
    //type:提现类型 0 金币 1积分
    public static String getUserWithdrawCashList(String type, String page, String limit) {
        try {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("appid", ApiConfig.getAppid());
            jsonObject.addProperty("usertoken", ApiConfig.getUserToken());
            jsonObject.addProperty("type", type);
            jsonObject.addProperty("page", page);
            jsonObject.addProperty("limit", limit);
            jsonObject.addProperty("timestamp", System.currentTimeMillis());

            return SignUtils.decrypt(OkHttpUtils.postJson(ApiConfig.getApi() + "get_user_billing", jsonObject.toString()));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //解绑QQ
    public static String unbindQq() {
        try {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("appid", ApiConfig.getAppid());
            jsonObject.addProperty("usertoken", ApiConfig.getUserToken());
            jsonObject.addProperty("timestamp", System.currentTimeMillis());

            return SignUtils.decrypt(OkHttpUtils.postJson(ApiConfig.getApi() + "unbind_qq", jsonObject.toString()));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //勋章的摘下及佩戴
    //type:操作类型 0佩戴 1摘下
    public static String medalWear(String medal_id,String type) {
        try {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("appid", ApiConfig.getAppid());
            jsonObject.addProperty("usertoken", ApiConfig.getUserToken());
            jsonObject.addProperty("medal_id", medal_id);
            jsonObject.addProperty("type", type);
            jsonObject.addProperty("timestamp", System.currentTimeMillis());

            return SignUtils.decrypt(OkHttpUtils.postJson(ApiConfig.getApi() + "medal_wear", jsonObject.toString()));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //获取当前用户已有的徽章
    //type:0勋章 1称号
    public static String getUserBadge(String type) {
        try {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("appid", ApiConfig.getAppid());
            jsonObject.addProperty("usertoken", ApiConfig.getUserToken());
            jsonObject.addProperty("type", type);
            jsonObject.addProperty("timestamp", System.currentTimeMillis());

            return SignUtils.decrypt(OkHttpUtils.postJson(ApiConfig.getApi() + "get_user_badge", jsonObject.toString()));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //获取当前在线用户列表
    public static String getOnlineUser() {
        try {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("appid", ApiConfig.getAppid());
            jsonObject.addProperty("timestamp", System.currentTimeMillis());

            return SignUtils.decrypt(OkHttpUtils.postJson(ApiConfig.getApi() + "get_online_user", jsonObject.toString()));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /*
     *
     * 卡密相关
     *
     * */

    //使用直冲卡密
    public static String applyDirectChargeKm(String km) {
        try {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("appid", ApiConfig.getAppid());
            jsonObject.addProperty("usertoken", ApiConfig.getUserToken());
            jsonObject.addProperty("km", km);
            jsonObject.addProperty("timestamp", System.currentTimeMillis());

            return SignUtils.decrypt(OkHttpUtils.postJson(ApiConfig.getApi() + "apply_direct_charge_km", jsonObject.toString()));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //使用登录卡密
    public static String applyLoginKm(String km) {
        try {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("appid", ApiConfig.getAppid());
            jsonObject.addProperty("device", ApiConfig.getDevice());
            jsonObject.addProperty("km", km);
            jsonObject.addProperty("timestamp", System.currentTimeMillis());

            return SignUtils.decrypt(OkHttpUtils.postJson(ApiConfig.getApi() + "apply_login_km", jsonObject.toString()));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //卡密自动登录
    public static String automaticLogin() {
        try {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("appid", ApiConfig.getAppid());
            jsonObject.addProperty("device", ApiConfig.getDevice());
            jsonObject.addProperty("timestamp", System.currentTimeMillis());

            return SignUtils.decrypt(OkHttpUtils.postJson(ApiConfig.getApi() + "automatic_login", jsonObject.toString()));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /*
     *
     * 商城相关
     *
     * */

    //商品列表
    public static String productList(String limit,String page) {
        try {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("appid", ApiConfig.getAppid());
            jsonObject.addProperty("limit", limit);
            jsonObject.addProperty("page", page);
            jsonObject.addProperty("timestamp", System.currentTimeMillis());

            return SignUtils.decrypt(OkHttpUtils.postJson(ApiConfig.getApi() + "product_list", jsonObject.toString()));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //商品信息
    public static String getProductInformation(String shopid) {
        try {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("appid", ApiConfig.getAppid());
            jsonObject.addProperty("shopid", shopid);
            jsonObject.addProperty("timestamp", System.currentTimeMillis());

            return SignUtils.decrypt(OkHttpUtils.postJson(ApiConfig.getApi() + "get_product_information", jsonObject.toString()));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //购买商品
    //payment_type:第三方支付需要传入此参数
    public static String buyGoods(String shopid,String payment_type) {
        try {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("appid", ApiConfig.getAppid());
            jsonObject.addProperty("usertoken", ApiConfig.getUserToken());
            jsonObject.addProperty("shopid", shopid);
            jsonObject.addProperty("payment_type", payment_type);
            jsonObject.addProperty("timestamp", System.currentTimeMillis());

            return SignUtils.decrypt(OkHttpUtils.postJson(ApiConfig.getApi() + "get_product_information", jsonObject.toString()));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //商品列表
    public static String getOrderRecord(String limit,String page) {
        try {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("appid", ApiConfig.getAppid());
            jsonObject.addProperty("usertoken", ApiConfig.getUserToken());
            jsonObject.addProperty("limit", limit);
            jsonObject.addProperty("page", page);
            jsonObject.addProperty("timestamp", System.currentTimeMillis());

            return SignUtils.decrypt(OkHttpUtils.postJson(ApiConfig.getApi() + "get_order_record", jsonObject.toString()));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /*
     *
     * 论坛相关
     *
     * */

    //获取板块列表
    public static String getSectionList() {
        try {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("appid", ApiConfig.getAppid());
            jsonObject.addProperty("usertoken", ApiConfig.getUserToken());
            jsonObject.addProperty("timestamp", System.currentTimeMillis());

            return SignUtils.decrypt(OkHttpUtils.postJson(ApiConfig.getApi() + "get_section_list", jsonObject.toString()));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //获取板块列表
    public static String getSectionInformation(String sectionid) {
        try {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("appid", ApiConfig.getAppid());
            jsonObject.addProperty("sectionid", sectionid);
            jsonObject.addProperty("timestamp", System.currentTimeMillis());

            return SignUtils.decrypt(OkHttpUtils.postJson(ApiConfig.getApi() + "get_section_information", jsonObject.toString()));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //获取帖子列表
    public static String getPostsList(String status,String sort,String sortOrder,String limit,String page) {
        try {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("appid", ApiConfig.getAppid());
            jsonObject.addProperty("usertoken", ApiConfig.getUserToken());
            jsonObject.addProperty("status", status);
            jsonObject.addProperty("sort", sort);
            jsonObject.addProperty("sortOrder", sortOrder);
            jsonObject.addProperty("limit", limit);
            jsonObject.addProperty("page", page);
            jsonObject.addProperty("timestamp", System.currentTimeMillis());

            return SignUtils.decrypt(OkHttpUtils.postJson(ApiConfig.getApi() + "get_posts_list", jsonObject.toString()));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //获取用户帖子列表
    public static String getUserPostsList(String userid,String status,String sort,String sortOrder,String limit,String page) {
        try {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("appid", ApiConfig.getAppid());
            jsonObject.addProperty("usertoken", ApiConfig.getUserToken());
            jsonObject.addProperty("userid", userid);
            jsonObject.addProperty("status", status);
            jsonObject.addProperty("sort", sort);
            jsonObject.addProperty("sortOrder", sortOrder);
            jsonObject.addProperty("limit", limit);
            jsonObject.addProperty("page", page);
            jsonObject.addProperty("timestamp", System.currentTimeMillis());

            return SignUtils.decrypt(OkHttpUtils.postJson(ApiConfig.getApi() + "get_posts_list", jsonObject.toString()));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //获取板块帖子列表
    public static String getSectionPostsList(String sectionid,String status,String sort,String sortOrder,String limit,String page) {
        try {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("appid", ApiConfig.getAppid());
            jsonObject.addProperty("usertoken", ApiConfig.getUserToken());
            jsonObject.addProperty("sectionid", sectionid);
            jsonObject.addProperty("status", status);
            jsonObject.addProperty("sort", sort);
            jsonObject.addProperty("sortOrder", sortOrder);
            jsonObject.addProperty("limit", limit);
            jsonObject.addProperty("page", page);
            jsonObject.addProperty("timestamp", System.currentTimeMillis());

            return SignUtils.decrypt(OkHttpUtils.postJson(ApiConfig.getApi() + "get_posts_list", jsonObject.toString()));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //获取子板块帖子列表
    public static String getSubSectionPostsList(String sub_sectionid,String status,String sort,String sortOrder,String limit,String page) {
        try {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("appid", ApiConfig.getAppid());
            jsonObject.addProperty("usertoken", ApiConfig.getUserToken());
            jsonObject.addProperty("sub_sectionid", sub_sectionid);
            jsonObject.addProperty("status", status);
            jsonObject.addProperty("sort", sort);
            jsonObject.addProperty("sortOrder", sortOrder);
            jsonObject.addProperty("limit", limit);
            jsonObject.addProperty("page", page);
            jsonObject.addProperty("timestamp", System.currentTimeMillis());

            return SignUtils.decrypt(OkHttpUtils.postJson(ApiConfig.getApi() + "get_posts_list", jsonObject.toString()));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //搜索帖子
    public static String searchPostsList(String keyword,String status,String sort,String sortOrder,String limit,String page) {
        try {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("appid", ApiConfig.getAppid());
            jsonObject.addProperty("usertoken", ApiConfig.getUserToken());
            jsonObject.addProperty("keyword", keyword);
            jsonObject.addProperty("status", status);
            jsonObject.addProperty("sort", sort);
            jsonObject.addProperty("sortOrder", sortOrder);
            jsonObject.addProperty("limit", limit);
            jsonObject.addProperty("page", page);
            jsonObject.addProperty("timestamp", System.currentTimeMillis());

            return SignUtils.decrypt(OkHttpUtils.postJson(ApiConfig.getApi() + "get_posts_list", jsonObject.toString()));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //获取推荐帖子(只有正常帖子才会推荐)
    public static String getRecommendedPosts(String keyword,String status,String sort,String sortOrder,String limit,String page) {
        try {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("appid", ApiConfig.getAppid());
            jsonObject.addProperty("usertoken", ApiConfig.getUserToken());
            jsonObject.addProperty("limit", limit);
            jsonObject.addProperty("page", page);
            jsonObject.addProperty("timestamp", System.currentTimeMillis());

            return SignUtils.decrypt(OkHttpUtils.postJson(ApiConfig.getApi() + "get_recommended_posts", jsonObject.toString()));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //获取帖子信息
    public static String getPostInformation(String postid) {
        try {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("appid", ApiConfig.getAppid());
            jsonObject.addProperty("usertoken", ApiConfig.getUserToken());
            jsonObject.addProperty("postid", postid);
            jsonObject.addProperty("timestamp", System.currentTimeMillis());

            return SignUtils.decrypt(OkHttpUtils.postJson(ApiConfig.getApi() + "get_recommended_posts", jsonObject.toString()));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //发布帖子
    public static String post(String title,String content,String subsectionid,List<String> path,String file,String paid_reading,String reading_price,String preview_word_count,String file_download_method,String file_download_price,String network_picture,String video_img) {
        try {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("appid", ApiConfig.getAppid());
            jsonObject.addProperty("usertoken", ApiConfig.getUserToken());
            jsonObject.addProperty("title", title);
            jsonObject.addProperty("content", content);
            jsonObject.addProperty("subsectionid", subsectionid);
            //当file_download_method=1或者2或者3的时候必填 该字段即可以上传文件又可以直接填写文件链接（前四位是http则会识别为文件链接）
            jsonObject.addProperty("file", file);
            //付费阅读 0免费1评论下载2金币付费3积分付费
            jsonObject.addProperty("paid_reading", paid_reading);
            //付费阅读价格 当paid_reading=2或者3的时候必填
            jsonObject.addProperty("reading_price", reading_price);
            //未付费文章预览字数 当paid_reading=1或者2或者3的时候必填
            jsonObject.addProperty("preview_word_count", preview_word_count);
            //附件下载方式 0 不做任何限制 1评论下载 2金币下载3积分下载
            jsonObject.addProperty("file_download_method", file_download_method);
            //附件下载价格 当file_download_method=1或者2或者3的时候必填
            jsonObject.addProperty("file_download_price", file_download_price);
            //网络图片,如果多个请用英文逗号隔开，到时候输出到img_url中
            jsonObject.addProperty("network_picture", network_picture);
            //视频封面 该字段即可以上传文件又可以直接填写文件链接（前四位是http则会识别为文件链接）
            jsonObject.addProperty("video_img", video_img);
            jsonObject.addProperty("timestamp", System.currentTimeMillis());

            return SignUtils.decrypt(OkHttpUtils.uploadMultipleFilesWithJson(ApiConfig.getApi() + "post", path, jsonObject.toString()));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //删除帖子
    public static String deletePost(String postid) {
        try {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("appid", ApiConfig.getAppid());
            jsonObject.addProperty("usertoken", ApiConfig.getUserToken());
            jsonObject.addProperty("postid", postid);
            jsonObject.addProperty("timestamp", System.currentTimeMillis());

            return SignUtils.decrypt(OkHttpUtils.postJson(ApiConfig.getApi() + "delete_post", jsonObject.toString()));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //发布评论
    public static String postComment(String postid,String content,String parentid,List<String> path,String img) {
        try {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("appid", ApiConfig.getAppid());
            jsonObject.addProperty("usertoken", ApiConfig.getUserToken());
            jsonObject.addProperty("postid", postid);
            jsonObject.addProperty("content", content);
            jsonObject.addProperty("parentid", parentid);
            //当file_download_method=1或者2或者3的时候必填 该字段即可以上传文件又可以直接填写文件链接（前四位是http则会识别为文件链接）
            jsonObject.addProperty("img", img);
            jsonObject.addProperty("timestamp", System.currentTimeMillis());

            return SignUtils.decrypt(OkHttpUtils.uploadMultipleFilesWithJson(ApiConfig.getApi() + "post_comment", path, jsonObject.toString()));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //编辑帖子
    public static String editPost(String title,String content,String postid,List<String> path,String file,String paid_reading,String reading_price,String preview_word_count,String file_download_method,String file_download_price,String network_picture,String video_img) {
        try {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("appid", ApiConfig.getAppid());
            jsonObject.addProperty("usertoken", ApiConfig.getUserToken());
            jsonObject.addProperty("title", title);
            jsonObject.addProperty("content", content);
            jsonObject.addProperty("postid", postid);
            //当file_download_method=1或者2或者3的时候必填 该字段即可以上传文件又可以直接填写文件链接（前四位是http则会识别为文件链接）
            jsonObject.addProperty("file", file);
            //付费阅读 0免费1评论下载2金币付费3积分付费
            jsonObject.addProperty("paid_reading", paid_reading);
            //付费阅读价格 当paid_reading=2或者3的时候必填
            jsonObject.addProperty("reading_price", reading_price);
            //未付费文章预览字数 当paid_reading=1或者2或者3的时候必填
            jsonObject.addProperty("preview_word_count", preview_word_count);
            //附件下载方式 0 不做任何限制 1评论下载 2金币下载3积分下载
            jsonObject.addProperty("file_download_method", file_download_method);
            //附件下载价格 当file_download_method=1或者2或者3的时候必填
            jsonObject.addProperty("file_download_price", file_download_price);
            //网络图片,如果多个请用英文逗号隔开，到时候输出到img_url中
            jsonObject.addProperty("network_picture", network_picture);
            //视频封面 该字段即可以上传文件又可以直接填写文件链接（前四位是http则会识别为文件链接）
            jsonObject.addProperty("video_img", video_img);
            jsonObject.addProperty("timestamp", System.currentTimeMillis());

            return SignUtils.decrypt(OkHttpUtils.uploadMultipleFilesWithJson(ApiConfig.getApi() + "edit_post", path, jsonObject.toString()));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //删除评论
    public static String deleteComment(String commentid) {
        try {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("appid", ApiConfig.getAppid());
            jsonObject.addProperty("usertoken", ApiConfig.getUserToken());
            jsonObject.addProperty("commentid", commentid);
            jsonObject.addProperty("timestamp", System.currentTimeMillis());

            return SignUtils.decrypt(OkHttpUtils.postJson(ApiConfig.getApi() + "delete_comment", jsonObject.toString()));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //获取评论列表
    public static String getListComments(String postid,String userid,String status,String comment_id,String sort,String sortOrder,String limit,String page) {
        try {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("appid", ApiConfig.getAppid());
            jsonObject.addProperty("postid", postid);
            jsonObject.addProperty("userid", userid);
            jsonObject.addProperty("status", status);
            jsonObject.addProperty("comment_id", comment_id);
            jsonObject.addProperty("sort", sort);
            jsonObject.addProperty("sortOrder", sortOrder);
            jsonObject.addProperty("limit", limit);
            jsonObject.addProperty("page", page);
            jsonObject.addProperty("timestamp", System.currentTimeMillis());

            return SignUtils.decrypt(OkHttpUtils.postJson(ApiConfig.getApi() + "get_list_comments", jsonObject.toString()));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //点赞/取消点赞帖子
    public static String likePosts(String postid) {
        try {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("appid", ApiConfig.getAppid());
            jsonObject.addProperty("usertoken", ApiConfig.getUserToken());
            jsonObject.addProperty("postid", postid);
            jsonObject.addProperty("timestamp", System.currentTimeMillis());

            return SignUtils.decrypt(OkHttpUtils.postJson(ApiConfig.getApi() + "like_posts", jsonObject.toString()));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //获取点赞记录
    public static String getLikesRecords(String limit,String page) {
        try {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("appid", ApiConfig.getAppid());
            jsonObject.addProperty("usertoken", ApiConfig.getUserToken());
            jsonObject.addProperty("limit", limit);
            jsonObject.addProperty("page", page);
            jsonObject.addProperty("timestamp", System.currentTimeMillis());

            return SignUtils.decrypt(OkHttpUtils.postJson(ApiConfig.getApi() + "get_likes_records", jsonObject.toString()));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //获取板块子版块点赞记录
    public static String getLikesRecords(String sectionid,String sub_sectionid,String limit,String page) {
        try {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("appid", ApiConfig.getAppid());
            jsonObject.addProperty("usertoken", ApiConfig.getUserToken());
            jsonObject.addProperty("sectionid", sectionid);
            jsonObject.addProperty("sub_sectionid", sub_sectionid);
            jsonObject.addProperty("limit", limit);
            jsonObject.addProperty("page", page);
            jsonObject.addProperty("timestamp", System.currentTimeMillis());

            return SignUtils.decrypt(OkHttpUtils.postJson(ApiConfig.getApi() + "get_likes_records", jsonObject.toString()));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //浏览历史
    public static String browseHistory(String limit,String page) {
        try {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("appid", ApiConfig.getAppid());
            jsonObject.addProperty("usertoken", ApiConfig.getUserToken());
            jsonObject.addProperty("limit", limit);
            jsonObject.addProperty("page", page);
            jsonObject.addProperty("timestamp", System.currentTimeMillis());

            return SignUtils.decrypt(OkHttpUtils.postJson(ApiConfig.getApi() + "browse_history", jsonObject.toString()));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //浏览历史
    public static String browseHistory(String sectionid,String sub_sectionid,String limit,String page) {
        try {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("appid", ApiConfig.getAppid());
            jsonObject.addProperty("usertoken", ApiConfig.getUserToken());
            jsonObject.addProperty("sectionid", sectionid);
            jsonObject.addProperty("sub_sectionid", sub_sectionid);
            jsonObject.addProperty("limit", limit);
            jsonObject.addProperty("page", page);
            jsonObject.addProperty("timestamp", System.currentTimeMillis());

            return SignUtils.decrypt(OkHttpUtils.postJson(ApiConfig.getApi() + "browse_history", jsonObject.toString()));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //审核帖子
    public static String reviewPosts(String postid,String status,String reason_review) {
        try {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("appid", ApiConfig.getAppid());
            jsonObject.addProperty("usertoken", ApiConfig.getUserToken());
            jsonObject.addProperty("postid", postid);
            jsonObject.addProperty("status", status);
            jsonObject.addProperty("reason_review", reason_review);
            jsonObject.addProperty("timestamp", System.currentTimeMillis());

            return SignUtils.decrypt(OkHttpUtils.postJson(ApiConfig.getApi() + "review_posts", jsonObject.toString()));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //获取待审核的帖子
    public static String getPendingReviewPosts(String limit,String page) {
        try {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("appid", ApiConfig.getAppid());
            jsonObject.addProperty("usertoken", ApiConfig.getUserToken());
            jsonObject.addProperty("limit", limit);
            jsonObject.addProperty("page", page);
            jsonObject.addProperty("timestamp", System.currentTimeMillis());

            return SignUtils.decrypt(OkHttpUtils.postJson(ApiConfig.getApi() + "get_pending_review_posts", jsonObject.toString()));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //置顶/热门/帖子状态修改
    //status:当type为0时（0不置顶1置顶）当type为1时（0普通1热门）当type为2时（0正常1文章锁定）当type为3时（0不是1是精华）
    //type:1置顶2热门3帖子状态4精华
    public static String postStatusModification(String postid,String type,String status) {
        try {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("appid", ApiConfig.getAppid());
            jsonObject.addProperty("usertoken", ApiConfig.getUserToken());
            jsonObject.addProperty("postid", postid);
            jsonObject.addProperty("type", type);
            jsonObject.addProperty("status", status);
            jsonObject.addProperty("timestamp", System.currentTimeMillis());

            return SignUtils.decrypt(OkHttpUtils.postJson(ApiConfig.getApi() + "post_status_modification", jsonObject.toString()));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //获取我的关注的帖子
    public static String getMyFollowingPosts(String sectionid,String sub_sectionid,String limit,String page) {
        try {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("appid", ApiConfig.getAppid());
            jsonObject.addProperty("usertoken", ApiConfig.getUserToken());
            jsonObject.addProperty("sectionid", sectionid);
            jsonObject.addProperty("sub_sectionid", sub_sectionid);
            jsonObject.addProperty("limit", limit);
            jsonObject.addProperty("page", page);
            jsonObject.addProperty("timestamp", System.currentTimeMillis());

            return SignUtils.decrypt(OkHttpUtils.postJson(ApiConfig.getApi() + "get_my_following_posts", jsonObject.toString()));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //获取我的关注的帖子
    public static String getMyFollowingPosts(String limit,String page) {
        try {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("appid", ApiConfig.getAppid());
            jsonObject.addProperty("usertoken", ApiConfig.getUserToken());
            jsonObject.addProperty("limit", limit);
            jsonObject.addProperty("page", page);
            jsonObject.addProperty("timestamp", System.currentTimeMillis());

            return SignUtils.decrypt(OkHttpUtils.postJson(ApiConfig.getApi() + "get_my_following_posts", jsonObject.toString()));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //打赏帖子
    public static String rewardPosts(String postid,String money,String payment) {
        try {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("appid", ApiConfig.getAppid());
            jsonObject.addProperty("usertoken", ApiConfig.getUserToken());
            jsonObject.addProperty("postid", postid);
            jsonObject.addProperty("money", money);
            jsonObject.addProperty("payment", payment);
            jsonObject.addProperty("timestamp", System.currentTimeMillis());

            return SignUtils.decrypt(OkHttpUtils.postJson(ApiConfig.getApi() + "reward_posts", jsonObject.toString()));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //对需要支付的帖子进行支付
    public static String payPost(String postid,String type) {
        try {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("appid", ApiConfig.getAppid());
            jsonObject.addProperty("usertoken", ApiConfig.getUserToken());
            jsonObject.addProperty("postid", postid);
            jsonObject.addProperty("type", type);
            jsonObject.addProperty("timestamp", System.currentTimeMillis());

            return SignUtils.decrypt(OkHttpUtils.postJson(ApiConfig.getApi() + "pay_post", jsonObject.toString()));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //获取关注用户的帖子列表
    public static String getFollowUserPost(String limit,String page) {
        try {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("appid", ApiConfig.getAppid());
            jsonObject.addProperty("usertoken", ApiConfig.getUserToken());
            jsonObject.addProperty("limit", limit);
            jsonObject.addProperty("page", page);
            jsonObject.addProperty("timestamp", System.currentTimeMillis());

            return SignUtils.decrypt(OkHttpUtils.postJson(ApiConfig.getApi() + "get_follow_user_post", jsonObject.toString()));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //对评论的打赏
    public static String rewardingComments(String commentid,String money,String payment) {
        try {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("appid", ApiConfig.getAppid());
            jsonObject.addProperty("usertoken", ApiConfig.getUserToken());
            jsonObject.addProperty("commentid", commentid);
            jsonObject.addProperty("money", money);
            jsonObject.addProperty("payment", payment);
            jsonObject.addProperty("timestamp", System.currentTimeMillis());

            return SignUtils.decrypt(OkHttpUtils.postJson(ApiConfig.getApi() + "rewarding_comments", jsonObject.toString()));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //收藏/取消收藏帖子
    public static String collectionPosts(String postid) {
        try {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("appid", ApiConfig.getAppid());
            jsonObject.addProperty("usertoken", ApiConfig.getUserToken());
            jsonObject.addProperty("postid", postid);
            jsonObject.addProperty("timestamp", System.currentTimeMillis());

            return SignUtils.decrypt(OkHttpUtils.postJson(ApiConfig.getApi() + "collection_posts", jsonObject.toString()));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //获取收藏记录
    public static String getCollectionRecords(String sectionid,String sub_sectionid,String limit,String page) {
        try {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("appid", ApiConfig.getAppid());
            jsonObject.addProperty("usertoken", ApiConfig.getUserToken());
            jsonObject.addProperty("sectionid", sectionid);
            jsonObject.addProperty("sub_sectionid", sub_sectionid);
            jsonObject.addProperty("limit", limit);
            jsonObject.addProperty("page", page);
            jsonObject.addProperty("timestamp", System.currentTimeMillis());

            return SignUtils.decrypt(OkHttpUtils.postJson(ApiConfig.getApi() + "get_collection_records", jsonObject.toString()));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //获取收藏记录
    public static String getCollectionRecords(String limit,String page) {
        try {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("appid", ApiConfig.getAppid());
            jsonObject.addProperty("usertoken", ApiConfig.getUserToken());
            jsonObject.addProperty("limit", limit);
            jsonObject.addProperty("page", page);
            jsonObject.addProperty("timestamp", System.currentTimeMillis());

            return SignUtils.decrypt(OkHttpUtils.postJson(ApiConfig.getApi() + "get_collection_records", jsonObject.toString()));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //获取举报标签
    public static String getLabels(String type) {
        try {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("appid", ApiConfig.getAppid());
            jsonObject.addProperty("type", type);
            jsonObject.addProperty("timestamp", System.currentTimeMillis());

            return SignUtils.decrypt(OkHttpUtils.postJson(ApiConfig.getApi() + "get_labels", jsonObject.toString()));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //举报帖子或评论
    public static String reporting(String type,String target_id,String tag,String content) {
        try {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("appid", ApiConfig.getAppid());
            jsonObject.addProperty("usertoken", ApiConfig.getUserToken());
            jsonObject.addProperty("type", type);
            jsonObject.addProperty("target_id", target_id);
            jsonObject.addProperty("tag", tag);
            jsonObject.addProperty("content", content);
            jsonObject.addProperty("timestamp", System.currentTimeMillis());

            return SignUtils.decrypt(OkHttpUtils.postJson(ApiConfig.getApi() + "reporting", jsonObject.toString()));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //获取用户举报记录
    public static String getUserReporting(String limit,String page) {
        try {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("appid", ApiConfig.getAppid());
            jsonObject.addProperty("usertoken", ApiConfig.getUserToken());
            jsonObject.addProperty("limit", limit);
            jsonObject.addProperty("page", page);
            jsonObject.addProperty("timestamp", System.currentTimeMillis());

            return SignUtils.decrypt(OkHttpUtils.postJson(ApiConfig.getApi() + "get_user_reporting", jsonObject.toString()));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //获取待审核评论列表
    public static String getPendingReviewComments(String limit,String page) {
        try {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("appid", ApiConfig.getAppid());
            jsonObject.addProperty("usertoken", ApiConfig.getUserToken());
            jsonObject.addProperty("limit", limit);
            jsonObject.addProperty("page", page);
            jsonObject.addProperty("timestamp", System.currentTimeMillis());

            return SignUtils.decrypt(OkHttpUtils.postJson(ApiConfig.getApi() + "get_pending_review_comments", jsonObject.toString()));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //审核评论
    public static String reviewComments(String commentid,String status,String reason_review) {
        try {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("appid", ApiConfig.getAppid());
            jsonObject.addProperty("usertoken", ApiConfig.getUserToken());
            jsonObject.addProperty("commentid", commentid);
            jsonObject.addProperty("status", status);
            jsonObject.addProperty("reason_review", reason_review);
            jsonObject.addProperty("timestamp", System.currentTimeMillis());

            return SignUtils.decrypt(OkHttpUtils.postJson(ApiConfig.getApi() + "review_comments", jsonObject.toString()));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //修改评论置顶状态
    public static String editCommentTop(String commentid,String is_top) {
        try {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("appid", ApiConfig.getAppid());
            jsonObject.addProperty("usertoken", ApiConfig.getUserToken());
            jsonObject.addProperty("commentid", commentid);
            jsonObject.addProperty("is_top", is_top);
            jsonObject.addProperty("timestamp", System.currentTimeMillis());

            return SignUtils.decrypt(OkHttpUtils.postJson(ApiConfig.getApi() + "edit_comment_top", jsonObject.toString()));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /*
     *
     * 消息相关
     *
     * */

    //获取消息通知
    public static String getMessageNotifications(String limit,String page) {
        try {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("appid", ApiConfig.getAppid());
            jsonObject.addProperty("usertoken", ApiConfig.getUserToken());
            jsonObject.addProperty("limit", limit);
            jsonObject.addProperty("page", page);
            jsonObject.addProperty("timestamp", System.currentTimeMillis());

            return SignUtils.decrypt(OkHttpUtils.postJson(ApiConfig.getApi() + "get_message_notifications", jsonObject.toString()));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //获取消息通知
    public static String getMessageNotifications(String type,String limit,String page) {
        try {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("appid", ApiConfig.getAppid());
            jsonObject.addProperty("usertoken", ApiConfig.getUserToken());
            jsonObject.addProperty("type", type);
            jsonObject.addProperty("limit", limit);
            jsonObject.addProperty("page", page);
            jsonObject.addProperty("timestamp", System.currentTimeMillis());

            return SignUtils.decrypt(OkHttpUtils.postJson(ApiConfig.getApi() + "get_message_notifications", jsonObject.toString()));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //获取未读消息通知数量
    public static String getUnreadMessageNotifications() {
        try {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("appid", ApiConfig.getAppid());
            jsonObject.addProperty("usertoken", ApiConfig.getUserToken());
            jsonObject.addProperty("timestamp", System.currentTimeMillis());

            return SignUtils.decrypt(OkHttpUtils.postJson(ApiConfig.getApi() + "get_unread_message_notifications", jsonObject.toString()));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //获取未读消息通知数量
    public static String getUnreadMessageNotifications(String type) {
        try {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("appid", ApiConfig.getAppid());
            jsonObject.addProperty("usertoken", ApiConfig.getUserToken());
            jsonObject.addProperty("type", type);
            jsonObject.addProperty("timestamp", System.currentTimeMillis());

            return SignUtils.decrypt(OkHttpUtils.postJson(ApiConfig.getApi() + "get_unread_message_notifications", jsonObject.toString()));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //一键清除消息通知
    public static String clearMessageNotification() {
        try {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("appid", ApiConfig.getAppid());
            jsonObject.addProperty("usertoken", ApiConfig.getUserToken());
            jsonObject.addProperty("timestamp", System.currentTimeMillis());

            return SignUtils.decrypt(OkHttpUtils.postJson(ApiConfig.getApi() + "clear_message_notification", jsonObject.toString()));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /*
     *
     * 笔记相关
     *
     * */

    //获取用户笔记
    public static String getNotesList(String limit,String page) {
        try {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("appid", ApiConfig.getAppid());
            jsonObject.addProperty("usertoken", ApiConfig.getUserToken());
            jsonObject.addProperty("limit", limit);
            jsonObject.addProperty("page", page);
            jsonObject.addProperty("timestamp", System.currentTimeMillis());

            return SignUtils.decrypt(OkHttpUtils.postJson(ApiConfig.getApi() + "get_notes_list", jsonObject.toString()));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //获取用户笔记详情
    public static String getNotesDetails(String notesid) {
        try {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("appid", ApiConfig.getAppid());
            jsonObject.addProperty("usertoken", ApiConfig.getUserToken());
            jsonObject.addProperty("notesid", notesid);
            jsonObject.addProperty("timestamp", System.currentTimeMillis());

            return SignUtils.decrypt(OkHttpUtils.postJson(ApiConfig.getApi() + "get_notes_details", jsonObject.toString()));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //修改笔记
    public static String updateNotes(String notesid,String title,String content) {
        try {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("appid", ApiConfig.getAppid());
            jsonObject.addProperty("usertoken", ApiConfig.getUserToken());
            jsonObject.addProperty("notesid", notesid);
            jsonObject.addProperty("title", title);
            jsonObject.addProperty("content", content);
            jsonObject.addProperty("timestamp", System.currentTimeMillis());

            return SignUtils.decrypt(OkHttpUtils.postJson(ApiConfig.getApi() + "update_notes", jsonObject.toString()));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //删除笔记
    public static String deleteNotes(String notesid) {
        try {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("appid", ApiConfig.getAppid());
            jsonObject.addProperty("usertoken", ApiConfig.getUserToken());
            jsonObject.addProperty("notesid", notesid);
            jsonObject.addProperty("timestamp", System.currentTimeMillis());

            return SignUtils.decrypt(OkHttpUtils.postJson(ApiConfig.getApi() + "delete_notes", jsonObject.toString()));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //添加笔记
    public static String addNotes(String title,String content) {
        try {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("appid", ApiConfig.getAppid());
            jsonObject.addProperty("usertoken", ApiConfig.getUserToken());
            jsonObject.addProperty("title", title);
            jsonObject.addProperty("content", content);
            jsonObject.addProperty("timestamp", System.currentTimeMillis());

            return SignUtils.decrypt(OkHttpUtils.postJson(ApiConfig.getApi() + "addNotes", jsonObject.toString()));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /*
     *
     * 私聊相关
     *
     * */

    //发送消息
    public static String sendMessage(String receiver_id,String content,String pid,List<String> path,String file,String type,String money,String message_type) {
        try {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("appid", ApiConfig.getAppid());
            jsonObject.addProperty("usertoken", ApiConfig.getUserToken());
            jsonObject.addProperty("receiver_id", receiver_id);
            jsonObject.addProperty("content", content);
            //回复的消息id
            jsonObject.addProperty("pid", pid);
            jsonObject.addProperty("file", file);
            //0金币转账1积分转账
            jsonObject.addProperty("type", type);
            //付费阅读价格 当paid_reading=2或者3的时候必填
            jsonObject.addProperty("money", money);
            //消息类型 0发送消息内容或图片 1转账 默认0（为了兼容以前的接口）
            jsonObject.addProperty("message_type", message_type);
            jsonObject.addProperty("timestamp", System.currentTimeMillis());

            return SignUtils.decrypt(OkHttpUtils.uploadMultipleFilesWithJson(ApiConfig.getApi() + "send_message", path, jsonObject.toString()));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //获取用户消息列表
    public static String getMessageList(String limit,String page) {
        try {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("appid", ApiConfig.getAppid());
            jsonObject.addProperty("usertoken", ApiConfig.getUserToken());
            jsonObject.addProperty("limit", limit);
            jsonObject.addProperty("page", page);
            jsonObject.addProperty("timestamp", System.currentTimeMillis());

            return SignUtils.decrypt(OkHttpUtils.postJson(ApiConfig.getApi() + "get_message_list", jsonObject.toString()));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //获取聊天记录
    public static String getChatLog(String receiver_id,String limit,String page) {
        try {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("appid", ApiConfig.getAppid());
            jsonObject.addProperty("usertoken", ApiConfig.getUserToken());
            jsonObject.addProperty("receiver_id", receiver_id);
            jsonObject.addProperty("limit", limit);
            jsonObject.addProperty("page", page);
            jsonObject.addProperty("timestamp", System.currentTimeMillis());

            return SignUtils.decrypt(OkHttpUtils.postJson(ApiConfig.getApi() + "get_chat_log", jsonObject.toString()));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /*
     *
     * 应用商店相关
     *
     * */

    //获取应用分类列表
    public static String appCategoryList(String limit,String page) {
        try {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("appid", ApiConfig.getAppid());
            jsonObject.addProperty("usertoken", ApiConfig.getUserToken());
            jsonObject.addProperty("limit", limit);
            jsonObject.addProperty("page", page);
            jsonObject.addProperty("timestamp", System.currentTimeMillis());

            return SignUtils.decrypt(OkHttpUtils.postJson(ApiConfig.getApi() + "app_category_list", jsonObject.toString()));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //获取分类的下级分类列表
    public static String appCategoryChildrenList(String pid,String limit,String page) {
        try {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("appid", ApiConfig.getAppid());
            jsonObject.addProperty("usertoken", ApiConfig.getUserToken());
            jsonObject.addProperty("pid", pid);
            jsonObject.addProperty("limit", limit);
            jsonObject.addProperty("page", page);
            jsonObject.addProperty("timestamp", System.currentTimeMillis());

            return SignUtils.decrypt(OkHttpUtils.postJson(ApiConfig.getApi() + "app_category_children_list", jsonObject.toString()));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //获取应用列表
    public static String getAppsList(String sort,String sortOrder,String limit,String page) {
        try {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("appid", ApiConfig.getAppid());
            jsonObject.addProperty("usertoken", ApiConfig.getUserToken());
            jsonObject.addProperty("sort", sort);
            jsonObject.addProperty("sortOrder", sortOrder);
            jsonObject.addProperty("limit", limit);
            jsonObject.addProperty("page", page);
            jsonObject.addProperty("timestamp", System.currentTimeMillis());

            return SignUtils.decrypt(OkHttpUtils.postJson(ApiConfig.getApi() + "get_apps_list", jsonObject.toString()));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //获取用户帖子列表
    public static String getUserAppsList(String userid,String sort,String sortOrder,String limit,String page) {
        try {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("appid", ApiConfig.getAppid());
            jsonObject.addProperty("usertoken", ApiConfig.getUserToken());
            jsonObject.addProperty("userid", userid);
            jsonObject.addProperty("sort", sort);
            jsonObject.addProperty("sortOrder", sortOrder);
            jsonObject.addProperty("limit", limit);
            jsonObject.addProperty("page", page);
            jsonObject.addProperty("timestamp", System.currentTimeMillis());

            return SignUtils.decrypt(OkHttpUtils.postJson(ApiConfig.getApi() + "get_apps_list", jsonObject.toString()));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //获取板块帖子列表
    public static String getCategoryAppsList(String category_id,String sort,String sortOrder,String limit,String page) {
        try {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("appid", ApiConfig.getAppid());
            jsonObject.addProperty("usertoken", ApiConfig.getUserToken());
            jsonObject.addProperty("category_id", category_id);
            jsonObject.addProperty("sort", sort);
            jsonObject.addProperty("sortOrder", sortOrder);
            jsonObject.addProperty("limit", limit);
            jsonObject.addProperty("page", page);
            jsonObject.addProperty("timestamp", System.currentTimeMillis());

            return SignUtils.decrypt(OkHttpUtils.postJson(ApiConfig.getApi() + "get_apps_list", jsonObject.toString()));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //获取子板块帖子列表
    public static String getSubCategoryAppsList(String sub_category_id,String sort,String sortOrder,String limit,String page) {
        try {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("appid", ApiConfig.getAppid());
            jsonObject.addProperty("usertoken", ApiConfig.getUserToken());
            jsonObject.addProperty("sub_category_id", sub_category_id);
            jsonObject.addProperty("sort", sort);
            jsonObject.addProperty("sortOrder", sortOrder);
            jsonObject.addProperty("limit", limit);
            jsonObject.addProperty("page", page);
            jsonObject.addProperty("timestamp", System.currentTimeMillis());

            return SignUtils.decrypt(OkHttpUtils.postJson(ApiConfig.getApi() + "get_apps_list", jsonObject.toString()));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //搜索帖子
    public static String searchAppsList(String appname,String sort,String sortOrder,String limit,String page) {
        try {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("appid", ApiConfig.getAppid());
            jsonObject.addProperty("usertoken", ApiConfig.getUserToken());
            jsonObject.addProperty("appname", appname);
            jsonObject.addProperty("sort", sort);
            jsonObject.addProperty("sortOrder", sortOrder);
            jsonObject.addProperty("limit", limit);
            jsonObject.addProperty("page", page);
            jsonObject.addProperty("timestamp", System.currentTimeMillis());

            return SignUtils.decrypt(OkHttpUtils.postJson(ApiConfig.getApi() + "get_apps_list", jsonObject.toString()));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //获取应用详情
    public static String getAppsInformation(String apps_id,String apps_version_id) {
        try {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("appid", ApiConfig.getAppid());
            jsonObject.addProperty("usertoken", ApiConfig.getUserToken());
            jsonObject.addProperty("apps_id", apps_id);
            jsonObject.addProperty("apps_version_id", apps_version_id);
            jsonObject.addProperty("timestamp", System.currentTimeMillis());

            return SignUtils.decrypt(OkHttpUtils.postJson(ApiConfig.getApi() + "get_apps_information", jsonObject.toString()));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //获取该应用的历史版本
    public static String getAppsHistoryVersion(String apps_id,String status) {
        try {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("appid", ApiConfig.getAppid());
            jsonObject.addProperty("usertoken", ApiConfig.getUserToken());
            jsonObject.addProperty("apps_id", apps_id);
            jsonObject.addProperty("status", status);
            jsonObject.addProperty("timestamp", System.currentTimeMillis());

            return SignUtils.decrypt(OkHttpUtils.postJson(ApiConfig.getApi() + "get_apps_history_version", jsonObject.toString()));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //发布应用
    public static String releaseApps(String appname,String icon,String app_size,List<String> path,String app_introduce,String app_introduction_image,String app_explain,String file,String is_pay,String pay_money,String app_version,String category_id,String sub_category_id,String update_content) {
        try {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("appid", ApiConfig.getAppid());
            jsonObject.addProperty("usertoken", ApiConfig.getUserToken());
            jsonObject.addProperty("appname", appname);
            jsonObject.addProperty("icon", icon);
            jsonObject.addProperty("app_size", app_size);
            //当file_download_method=1或者2或者3的时候必填 该字段即可以上传文件又可以直接填写文件链接（前四位是http则会识别为文件链接）
            jsonObject.addProperty("app_introduce", app_introduce);
            //付费阅读 0免费1评论下载2金币付费3积分付费
            jsonObject.addProperty("app_introduction_image", app_introduction_image);
            //付费阅读价格 当paid_reading=2或者3的时候必填
            jsonObject.addProperty("app_explain", app_explain);
            //未付费文章预览字数 当paid_reading=1或者2或者3的时候必填
            jsonObject.addProperty("file", file);
            //附件下载方式 0 不做任何限制 1评论下载 2金币下载3积分下载
            jsonObject.addProperty("is_pay", is_pay);
            //附件下载价格 当file_download_method=1或者2或者3的时候必填
            jsonObject.addProperty("pay_money", pay_money);
            //网络图片,如果多个请用英文逗号隔开，到时候输出到img_url中
            jsonObject.addProperty("app_version", app_version);
            //视频封面 该字段即可以上传文件又可以直接填写文件链接（前四位是http则会识别为文件链接）
            jsonObject.addProperty("category_id", category_id);
            jsonObject.addProperty("sub_category_id", sub_category_id);
            //视频封面 该字段即可以上传文件又可以直接填写文件链接（前四位是http则会识别为文件链接）
            jsonObject.addProperty("update_content", update_content);
            jsonObject.addProperty("timestamp", System.currentTimeMillis());

            return SignUtils.decrypt(OkHttpUtils.uploadMultipleFilesWithJson(ApiConfig.getApi() + "release_apps", path, jsonObject.toString()));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //发布新版本
    public static String releaseNewVersion(String apps_id,String appname,String icon,String app_size,List<String> path,String app_introduce,String app_introduction_image,String app_explain,String file,String is_pay,String pay_money,String app_version,String category_id,String sub_category_id,String update_content) {
        try {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("appid", ApiConfig.getAppid());
            jsonObject.addProperty("usertoken", ApiConfig.getUserToken());
            jsonObject.addProperty("apps_id", apps_id);
            jsonObject.addProperty("appname", appname);
            jsonObject.addProperty("icon", icon);
            jsonObject.addProperty("app_size", app_size);
            //当file_download_method=1或者2或者3的时候必填 该字段即可以上传文件又可以直接填写文件链接（前四位是http则会识别为文件链接）
            jsonObject.addProperty("app_introduce", app_introduce);
            //付费阅读 0免费1评论下载2金币付费3积分付费
            jsonObject.addProperty("app_introduction_image", app_introduction_image);
            //付费阅读价格 当paid_reading=2或者3的时候必填
            jsonObject.addProperty("app_explain", app_explain);
            //未付费文章预览字数 当paid_reading=1或者2或者3的时候必填
            jsonObject.addProperty("file", file);
            //附件下载方式 0 不做任何限制 1评论下载 2金币下载3积分下载
            jsonObject.addProperty("is_pay", is_pay);
            //附件下载价格 当file_download_method=1或者2或者3的时候必填
            jsonObject.addProperty("pay_money", pay_money);
            //网络图片,如果多个请用英文逗号隔开，到时候输出到img_url中
            jsonObject.addProperty("app_version", app_version);
            //视频封面 该字段即可以上传文件又可以直接填写文件链接（前四位是http则会识别为文件链接）
            jsonObject.addProperty("category_id", category_id);
            jsonObject.addProperty("sub_category_id", sub_category_id);
            //视频封面 该字段即可以上传文件又可以直接填写文件链接（前四位是http则会识别为文件链接）
            jsonObject.addProperty("update_content", update_content);
            jsonObject.addProperty("timestamp", System.currentTimeMillis());

            return SignUtils.decrypt(OkHttpUtils.uploadMultipleFilesWithJson(ApiConfig.getApi() + "release_new_version", path, jsonObject.toString()));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //修改应用信息
    public static String editAppsInfo(String apps_id,String appname,String icon,String app_size,List<String> path,String app_introduce,String app_introduction_image,String app_explain,String file,String is_pay,String pay_money,String app_version,String category_id,String sub_category_id,String update_content) {
        try {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("appid", ApiConfig.getAppid());
            jsonObject.addProperty("usertoken", ApiConfig.getUserToken());
            jsonObject.addProperty("apps_id", apps_id);
            jsonObject.addProperty("appname", appname);
            jsonObject.addProperty("icon", icon);
            jsonObject.addProperty("app_size", app_size);
            //当file_download_method=1或者2或者3的时候必填 该字段即可以上传文件又可以直接填写文件链接（前四位是http则会识别为文件链接）
            jsonObject.addProperty("app_introduce", app_introduce);
            //付费阅读 0免费1评论下载2金币付费3积分付费
            jsonObject.addProperty("app_introduction_image", app_introduction_image);
            //付费阅读价格 当paid_reading=2或者3的时候必填
            jsonObject.addProperty("app_explain", app_explain);
            //未付费文章预览字数 当paid_reading=1或者2或者3的时候必填
            jsonObject.addProperty("file", file);
            //附件下载方式 0 不做任何限制 1评论下载 2金币下载3积分下载
            jsonObject.addProperty("is_pay", is_pay);
            //附件下载价格 当file_download_method=1或者2或者3的时候必填
            jsonObject.addProperty("pay_money", pay_money);
            //网络图片,如果多个请用英文逗号隔开，到时候输出到img_url中
            jsonObject.addProperty("app_version", app_version);
            //视频封面 该字段即可以上传文件又可以直接填写文件链接（前四位是http则会识别为文件链接）
            jsonObject.addProperty("category_id", category_id);
            jsonObject.addProperty("sub_category_id", sub_category_id);
            //视频封面 该字段即可以上传文件又可以直接填写文件链接（前四位是http则会识别为文件链接）
            jsonObject.addProperty("update_content", update_content);
            jsonObject.addProperty("timestamp", System.currentTimeMillis());

            return SignUtils.decrypt(OkHttpUtils.uploadMultipleFilesWithJson(ApiConfig.getApi() + "edit_apps_info", path, jsonObject.toString()));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //获取用户自己已上传的应用列表
    public static String getUserAppsList(String limit,String page) {
        try {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("appid", ApiConfig.getAppid());
            jsonObject.addProperty("usertoken", ApiConfig.getUserToken());
            jsonObject.addProperty("limit", limit);
            jsonObject.addProperty("page", page);
            jsonObject.addProperty("timestamp", System.currentTimeMillis());

            return SignUtils.decrypt(OkHttpUtils.postJson(ApiConfig.getApi() + "get_user_apps_list", jsonObject.toString()));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //获取评论列表
    public static String getAppsCommentList(String apps_id,String apps_version_id,String comment_id,String sort,String sortOrder,String limit,String page) {
        try {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("appid", ApiConfig.getAppid());
            jsonObject.addProperty("apps_id", apps_id);
            jsonObject.addProperty("apps_version_id", apps_version_id);
            jsonObject.addProperty("comment_id", comment_id);
            jsonObject.addProperty("sort", sort);
            jsonObject.addProperty("sortOrder", sortOrder);
            jsonObject.addProperty("limit", limit);
            jsonObject.addProperty("page", page);
            jsonObject.addProperty("timestamp", System.currentTimeMillis());

            return SignUtils.decrypt(OkHttpUtils.postJson(ApiConfig.getApi() + "get_apps_comment_list", jsonObject.toString()));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //获取用户评论列表
    public static String getUserAppsCommentList(String userid,String comment_id,String sort,String sortOrder,String limit,String page) {
        try {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("appid", ApiConfig.getAppid());
            jsonObject.addProperty("userid", userid);
            jsonObject.addProperty("comment_id", comment_id);
            jsonObject.addProperty("sort", sort);
            jsonObject.addProperty("sortOrder", sortOrder);
            jsonObject.addProperty("limit", limit);
            jsonObject.addProperty("page", page);
            jsonObject.addProperty("timestamp", System.currentTimeMillis());

            return SignUtils.decrypt(OkHttpUtils.postJson(ApiConfig.getApi() + "get_apps_comment_list", jsonObject.toString()));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //发布评论
    public static String appsAddComment(String apps_id,String apps_version_id,String content,String parentid,List<String> path,String img) {
        try {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("appid", ApiConfig.getAppid());
            jsonObject.addProperty("usertoken", ApiConfig.getUserToken());
            jsonObject.addProperty("apps_id", apps_id);
            jsonObject.addProperty("apps_version_id", apps_version_id);
            jsonObject.addProperty("content", content);
            jsonObject.addProperty("parentid", parentid);
            //当file_download_method=1或者2或者3的时候必填 该字段即可以上传文件又可以直接填写文件链接（前四位是http则会识别为文件链接）
            jsonObject.addProperty("img", img);
            jsonObject.addProperty("timestamp", System.currentTimeMillis());

            return SignUtils.decrypt(OkHttpUtils.uploadMultipleFilesWithJson(ApiConfig.getApi() + "apps_add_comment", path, jsonObject.toString()));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //删除应用或应用版本
    public static String deleteApps(String apps_id,String app_version_id) {
        try {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("appid", ApiConfig.getAppid());
            jsonObject.addProperty("usertoken", ApiConfig.getUserToken());
            jsonObject.addProperty("apps_id", apps_id);
            jsonObject.addProperty("app_version_id", app_version_id);
            jsonObject.addProperty("timestamp", System.currentTimeMillis());

            return SignUtils.decrypt(OkHttpUtils.postJson(ApiConfig.getApi() + "delete_apps", jsonObject.toString()));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //删除评论
    public static String deleteAppsComment(String commentid) {
        try {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("appid", ApiConfig.getAppid());
            jsonObject.addProperty("usertoken", ApiConfig.getUserToken());
            jsonObject.addProperty("commentid", commentid);
            jsonObject.addProperty("timestamp", System.currentTimeMillis());

            return SignUtils.decrypt(OkHttpUtils.postJson(ApiConfig.getApi() + "delete_apps_comment", jsonObject.toString()));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //增加下载量
    public static String addDownloadCount(String apps_id,String app_version_id) {
        try {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("appid", ApiConfig.getAppid());
            jsonObject.addProperty("usertoken", ApiConfig.getUserToken());
            jsonObject.addProperty("apps_id", apps_id);
            jsonObject.addProperty("app_version_id", app_version_id);
            jsonObject.addProperty("timestamp", System.currentTimeMillis());

            return SignUtils.decrypt(OkHttpUtils.postJson(ApiConfig.getApi() + "add_download_count", jsonObject.toString()));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //对需要支付的应用进行支付
    public static String payForApps(String apps_id,String app_version_id) {
        try {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("appid", ApiConfig.getAppid());
            jsonObject.addProperty("usertoken", ApiConfig.getUserToken());
            jsonObject.addProperty("apps_id", apps_id);
            jsonObject.addProperty("app_version_id", app_version_id);
            jsonObject.addProperty("timestamp", System.currentTimeMillis());

            return SignUtils.decrypt(OkHttpUtils.postJson(ApiConfig.getApi() + "pay_for_apps", jsonObject.toString()));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //对应用打赏
    public static String rewardForApps(String apps_id,String app_version_id,String money,String type) {
        try {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("appid", ApiConfig.getAppid());
            jsonObject.addProperty("usertoken", ApiConfig.getUserToken());
            jsonObject.addProperty("apps_id", apps_id);
            jsonObject.addProperty("app_version_id", app_version_id);
            jsonObject.addProperty("money", money);
            jsonObject.addProperty("type", type);
            jsonObject.addProperty("timestamp", System.currentTimeMillis());

            return SignUtils.decrypt(OkHttpUtils.postJson(ApiConfig.getApi() + "pay_for_apps", jsonObject.toString()));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /*
     *
     * 其他相关
     *
     * */

    //发送邮件接口
    public static String sendEmail(String email,String title,String content,String appkey) {
        try {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("appid", ApiConfig.getAppid());
            jsonObject.addProperty("email", email);
            jsonObject.addProperty("title", title);
            jsonObject.addProperty("content", content);
            jsonObject.addProperty("appkey", appkey);
            jsonObject.addProperty("timestamp", System.currentTimeMillis());

            return SignUtils.decrypt(OkHttpUtils.postJson(ApiConfig.getApi() + "send_email", jsonObject.toString()));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 上传文件并返回直链
     *
     * @param path 文件路径列表
     * @return 上传结果
     */
    public static String upload(List<String> path) {
        try {
            // 创建一个JsonObject对象
            JsonObject jsonObject = new JsonObject();
            // 向jsonObject中添加属性appid，其值为Config.getAppid()
            jsonObject.addProperty("appid", ApiConfig.getAppid());
            // 向jsonObject中添加属性usertoken，其值为Config.getUserToken()
            jsonObject.addProperty("usertoken", ApiConfig.getUserToken());
            // 向jsonObject中添加属性timestamp，其值为当前时间戳
            jsonObject.addProperty("timestamp", System.currentTimeMillis());

            // 初始化一个字符串变量result，其值为null
            String result = null;

            // 判断Config.getKey()是否为null
            if(ApiConfig.getKey()==null){
                // 如果Config.getKey()为null，则调用OkHttpUtils.uploadMultipleFilesWithJson方法上传文件，并将返回的结果赋值给result
                result = OkHttpUtils.uploadMultipleFilesWithJson(ApiConfig.getApi() + "upload", path, jsonObject.toString());
            }else{
                // 如果Config.getKey()不为null，则调用SignUtils.decrypt方法对上传结果进行解密，并将解密后的结果赋值给result
                result = SignUtils.decrypt(OkHttpUtils.uploadMultipleFilesWithJson(ApiConfig.getApi() + "upload", path, jsonObject.toString()));
            }
            // 返回result
            return result;
        } catch (Exception e) {
            // 如果发生异常，则抛出一个运行时异常
            throw new RuntimeException(e);
        }
    }

}