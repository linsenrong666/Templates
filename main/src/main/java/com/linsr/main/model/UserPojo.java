package com.linsr.main.model;

import com.linsr.common.model.BasePojo;
import com.linsr.common.model.BizPojo;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Description
 *
 * @author Linsr 2019/1/6 下午9:15
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class UserPojo extends BizPojo {


    /**
     * total_dividend :
     * total_redpacket :
     * user_info : {"user_id":"33914","wxid":"","nickname":"","username":"qqqq","truename":"","user_rank":"0","user_status":"0","user_type":"0","share":"0.0000","fixed_share":"0","rec_user":"","rec_userid":"0","pos_user":"","pos_userid":"0","position":"","rec_position":"","t0_user":"","t0_userid":"0","t1_user":"","t1_userid":"0","t2_user":"","t2_userid":"0","t3_user":"","t3_userid":"0","t4_user":"","t4_userid":"0","t5_user":"","t5_userid":"0","t6_user":"","t6_userid":"0","t7_user":"","t7_userid":"0","t8_user":"","t8_userid":"0","p0_user":"","p0_userid":"0","p1_user":"","p1_userid":"0","p2_user":"","p2_userid":"0","p3_user":"","p3_userid":"0","p4_user":"","p4_userid":"0","p5_user":"","p5_userid":"0","p6_user":"","p6_userid":"0","p7_user":"","p7_userid":"0","p8_user":"","p8_userid":"0","p9_user":"","p9_userid":"0","pos_count":"0","pos_num":"0","atv_userid":"0","up_userid":"0","userface":"","qrimg":"","ec_salt":"","password":"343b1c4a3ea721b2d640fc8700db0f36","repassword":"qqqqqq","idcard":"","mobile":"13344445555","email":"","question":"","answer":"","sex":"0","birthday":"0000-00-00","user_money":"0.00","frozen_money":"0.00","pay_points":"0.00","rank_points":"0.00","match_points":"0.00","address_id":"0","address":"","wxname":"","area":"","bankname":"","bankuser":"","bankcard":"","reg_time":"0","last_login":"0","last_time":"0000-00-00 00:00:00","last_ip":"124.64.17.55","visit_count":"177","is_special":"0","salt":"","parent_id":"0","flag":"0","msn":"","qq":"","office_phone":"","home_phone":"","is_validated":"0","credit_line":"0.00","passwd_question":"","passwd_answer":"","alias":"","wxch_bd":"","aite_id":"","check_time":"0","up_time":"0","is_user":"0","is_pifa":"0","pifa_user":"","pifa_userid":"0","is_power":"0","power_time":"0","is_send":"0","is_share":"0","red_num":"0","red_time":"0","is_new":"0","is_sendshare":"0","status":"1","token":"7864d06a14c5c422e8b1571c39f0b9f7","rec_nickname":""}
     */

    private String total_dividend;
    private String total_redpacket;
    private UserInfoBean user_info;

    @EqualsAndHashCode(callSuper = true)
    @Data
    public static class UserInfoBean extends BasePojo {
        /**
         * user_id : 33914
         * wxid :
         * nickname :
         * username : qqqq
         * truename :
         * user_rank : 0
         * user_status : 0
         * user_type : 0
         * share : 0.0000
         * fixed_share : 0
         * rec_user :
         * rec_userid : 0
         * pos_user :
         * pos_userid : 0
         * position :
         * rec_position :
         * t0_user :
         * t0_userid : 0
         * t1_user :
         * t1_userid : 0
         * t2_user :
         * t2_userid : 0
         * t3_user :
         * t3_userid : 0
         * t4_user :
         * t4_userid : 0
         * t5_user :
         * t5_userid : 0
         * t6_user :
         * t6_userid : 0
         * t7_user :
         * t7_userid : 0
         * t8_user :
         * t8_userid : 0
         * p0_user :
         * p0_userid : 0
         * p1_user :
         * p1_userid : 0
         * p2_user :
         * p2_userid : 0
         * p3_user :
         * p3_userid : 0
         * p4_user :
         * p4_userid : 0
         * p5_user :
         * p5_userid : 0
         * p6_user :
         * p6_userid : 0
         * p7_user :
         * p7_userid : 0
         * p8_user :
         * p8_userid : 0
         * p9_user :
         * p9_userid : 0
         * pos_count : 0
         * pos_num : 0
         * atv_userid : 0
         * up_userid : 0
         * userface :
         * qrimg :
         * ec_salt :
         * password : 343b1c4a3ea721b2d640fc8700db0f36
         * repassword : qqqqqq
         * idcard :
         * mobile : 13344445555
         * email :
         * question :
         * answer :
         * sex : 0
         * birthday : 0000-00-00
         * user_money : 0.00
         * frozen_money : 0.00
         * pay_points : 0.00
         * rank_points : 0.00
         * match_points : 0.00
         * address_id : 0
         * address :
         * wxname :
         * area :
         * bankname :
         * bankuser :
         * bankcard :
         * reg_time : 0
         * last_login : 0
         * last_time : 0000-00-00 00:00:00
         * last_ip : 124.64.17.55
         * visit_count : 177
         * is_special : 0
         * salt :
         * parent_id : 0
         * flag : 0
         * msn :
         * qq :
         * office_phone :
         * home_phone :
         * is_validated : 0
         * credit_line : 0.00
         * passwd_question :
         * passwd_answer :
         * alias :
         * wxch_bd :
         * aite_id :
         * check_time : 0
         * up_time : 0
         * is_user : 0
         * is_pifa : 0
         * pifa_user :
         * pifa_userid : 0
         * is_power : 0
         * power_time : 0
         * is_send : 0
         * is_share : 0
         * red_num : 0
         * red_time : 0
         * is_new : 0
         * is_sendshare : 0
         * status : 1
         * token : 7864d06a14c5c422e8b1571c39f0b9f7
         * rec_nickname :
         */

        private String user_id;
        private String wxid;
        private String nickname;
        private String username;
        private String truename;
        private String user_rank;
        private String user_status;
        private String user_type;
        private String share;
        private String fixed_share;
        private String rec_user;
        private String rec_userid;
        private String pos_user;
        private String pos_userid;
        private String position;
        private String rec_position;
        private String t0_user;
        private String t0_userid;
        private String t1_user;
        private String t1_userid;
        private String t2_user;
        private String t2_userid;
        private String t3_user;
        private String t3_userid;
        private String t4_user;
        private String t4_userid;
        private String t5_user;
        private String t5_userid;
        private String t6_user;
        private String t6_userid;
        private String t7_user;
        private String t7_userid;
        private String t8_user;
        private String t8_userid;
        private String p0_user;
        private String p0_userid;
        private String p1_user;
        private String p1_userid;
        private String p2_user;
        private String p2_userid;
        private String p3_user;
        private String p3_userid;
        private String p4_user;
        private String p4_userid;
        private String p5_user;
        private String p5_userid;
        private String p6_user;
        private String p6_userid;
        private String p7_user;
        private String p7_userid;
        private String p8_user;
        private String p8_userid;
        private String p9_user;
        private String p9_userid;
        private String pos_count;
        private String pos_num;
        private String atv_userid;
        private String up_userid;
        private String userface;
        private String qrimg;
        private String ec_salt;
        private String password;
        private String repassword;
        private String idcard;
        private String mobile;
        private String email;
        private String question;
        private String answer;
        private String sex;
        private String birthday;
        private String user_money;
        private String frozen_money;
        private String pay_points;
        private String rank_points;
        private String match_points;
        private String address_id;
        private String address;
        private String wxname;
        private String area;
        private String bankname;
        private String bankuser;
        private String bankcard;
        private String reg_time;
        private String last_login;
        private String last_time;
        private String last_ip;
        private String visit_count;
        private String is_special;
        private String salt;
        private String parent_id;
        private String flag;
        private String msn;
        private String qq;
        private String office_phone;
        private String home_phone;
        private String is_validated;
        private String credit_line;
        private String passwd_question;
        private String passwd_answer;
        private String alias;
        private String wxch_bd;
        private String aite_id;
        private String check_time;
        private String up_time;
        private String is_user;
        private String is_pifa;
        private String pifa_user;
        private String pifa_userid;
        private String is_power;
        private String power_time;
        private String is_send;
        private String is_share;
        private String red_num;
        private String red_time;
        private String is_new;
        private String is_sendshare;
        private String status;
        private String token;
        private String rec_nickname;

    }
}
