package cn.authing.api.ut;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import cn.authing.api.R;
import cn.authing.api.ut.v3.TestCaseV3Util;

public class UTActivity extends AppCompatActivity implements ExpandableListView.OnGroupClickListener, ExpandableListView.OnChildClickListener {

    private static final String[] mParentMenu = {
            "注册", "登录", "社会化登录", "扫码登录", "发送验证码", "获取用户信息", "更新用户信息", "MFA", "退出登录/注销账号", "Token", "账号绑定"
    };
    private GroupListAdapter mAdapter;
    private List<List<TestCase>> mChildMenu;
    private ArrayList<TestCase> mAllTestList;
    int type = 2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ut);

        mChildMenu = new ArrayList<>();
        mAllTestList = new ArrayList<>();
        ActionBar actionBar = getSupportActionBar();
        initV3Data();
        if (actionBar != null) {
            actionBar.setTitle("场景测试-api-v3");
        }

        ExpandableListView expandableListView = findViewById(R.id.expand_list);
        expandableListView.setOnGroupClickListener(this);
        expandableListView.setOnChildClickListener(this);

        List<GroupListParent> groupList = new ArrayList<>();
        for (int i = 0; i < mParentMenu.length; i++) {
            List<GroupListChild> childList = new ArrayList<>();
            for (int j = 0; j < mChildMenu.get(i).size(); j++) {
                String caseName = mChildMenu.get(i).get(j).getCaseName();
                String caseSubName = mChildMenu.get(i).get(j).getCaseSubName();
                childList.add(new GroupListChild(caseName + ": " + caseSubName));
            }
            groupList.add(new GroupListParent(mParentMenu[i], childList));
        }
        if (actionBar != null) {
            actionBar.setSubtitle(mAllTestList.size()+"");
        }
        mAdapter = new GroupListAdapter(this, groupList);
        expandableListView.setAdapter(mAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.test, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.menu_test) {
            Intent intent = new Intent(this, UTTestAllActivity.class);
            intent.putParcelableArrayListExtra("data", mAllTestList);
            intent.putExtra("type", type);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
        return false;
    }


    @Override
    public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
        Intent intent = new Intent(this, UTTestActivity.class);
        intent.putExtra("data", mChildMenu.get(groupPosition).get(childPosition));
        intent.putExtra("type", type);
        startActivity(intent);
        return false;
    }


    private void initV3Data(){
        List<TestCase> registerList = new ArrayList<>();
        registerList.add(TestCaseV3Util.createRegisterByEmailCase(0, false));
        registerList.add(TestCaseV3Util.createRegisterByEmailCase(1, false));
        registerList.add(TestCaseV3Util.createRegisterByEmailCase(2, false));
        registerList.add(TestCaseV3Util.createRegisterByPhoneCodeCase(0, false));
        registerList.add(TestCaseV3Util.createRegisterByPhoneCodeCase(1, false));
        registerList.add(TestCaseV3Util.createRegisterByPhoneCodeCase(2, false));
        registerList.add(TestCaseV3Util.createRegisterByEmailCodeCase(0, false));
        registerList.add(TestCaseV3Util.createRegisterByEmailCodeCase(1, false));
        registerList.add(TestCaseV3Util.createRegisterByEmailCodeCase(2, false));
        mChildMenu.add(registerList);
        mAllTestList.addAll(registerList);

        List<TestCase> loginList = new ArrayList<>();
        loginList.add(TestCaseV3Util.createLoginByAccountCase(0, false));
        loginList.add(TestCaseV3Util.createLoginByAccountCase(1, false));
        loginList.add(TestCaseV3Util.createLoginByAccountCase(2, false));
        loginList.add(TestCaseV3Util.createLoginByAccountCase(3, false));
        loginList.add(TestCaseV3Util.createLoginByAccountCase(4, false));
        loginList.add(TestCaseV3Util.createLoginByPhoneCodeCase(0, false));
        loginList.add(TestCaseV3Util.createLoginByPhoneCodeCase(1, false));
        loginList.add(TestCaseV3Util.createLoginByPhoneCodeCase(2, false));
        loginList.add(TestCaseV3Util.createLoginByEmailCodeCase(0, false));
        loginList.add(TestCaseV3Util.createLoginByEmailCodeCase(1, false));
        loginList.add(TestCaseV3Util.createLoginByEmailCodeCase(2, false));
        mChildMenu.add(loginList);
        mAllTestList.addAll(loginList);

        List<TestCase> socialList = new ArrayList<>();
        socialList.add(TestCaseV3Util.createSocialLoginCase(0, false));
        socialList.add(TestCaseV3Util.createSocialLoginCase(1, false));
        socialList.add(TestCaseV3Util.createSocialLoginCase(2, false));
        socialList.add(TestCaseV3Util.createSocialLoginCase(3, false));
        socialList.add(TestCaseV3Util.createSocialLoginCase(4, false));
        socialList.add(TestCaseV3Util.createSocialLoginCase(5, false));
        socialList.add(TestCaseV3Util.createSocialLoginCase(6, false));
        mChildMenu.add(socialList);
        mAllTestList.addAll(socialList);

        List<TestCase> qrList = new ArrayList<>();
        qrList.add(TestCaseV3Util.createQrLoginCase(0, false));
        qrList.add(TestCaseV3Util.createQrLoginCase(1, false));
        qrList.add(TestCaseV3Util.createQrLoginCase(2, false));
        mChildMenu.add(qrList);
        mAllTestList.addAll(qrList);

        List<TestCase> sendVerifyCodeList = new ArrayList<>();
        sendVerifyCodeList.add(TestCaseV3Util.createSendSmsCodeCase(0 ));
        sendVerifyCodeList.add(TestCaseV3Util.createSendSmsCodeCase(1));
        sendVerifyCodeList.add(TestCaseV3Util.createSendSmsCodeCase(2));
        sendVerifyCodeList.add(TestCaseV3Util.createSendSmsCodeCase(3));
        sendVerifyCodeList.add(TestCaseV3Util.createSendEmailCodeCase(0));
        sendVerifyCodeList.add(TestCaseV3Util.createSendEmailCodeCase(1));
        sendVerifyCodeList.add(TestCaseV3Util.createSendEmailCodeCase(2));
        mChildMenu.add(sendVerifyCodeList);
        mAllTestList.addAll(sendVerifyCodeList);

        List<TestCase> getUserInfoList = new ArrayList<>();
        getUserInfoList.add(TestCaseV3Util.createGetUerInfoCase());
        getUserInfoList.add(TestCaseV3Util.createGetSecurityInfoCase());
        getUserInfoList.add(TestCaseV3Util.createGetCustomUserDataCase());
        getUserInfoList.add(TestCaseV3Util.createGetLoggedHistoryCase());
        getUserInfoList.add(TestCaseV3Util.createGetLoggedApplicationCase());
        getUserInfoList.add(TestCaseV3Util.createGetTenantListCase());
        getUserInfoList.add(TestCaseV3Util.createGetDepartmentListCase());
        getUserInfoList.add(TestCaseV3Util.createGetListRolesCase());
        getUserInfoList.add(TestCaseV3Util.createGetListApplicationsCase());
        getUserInfoList.add(TestCaseV3Util.createGetListAuthorizedResourcesCase());
        getUserInfoList.add(TestCaseV3Util.createGetListOrgsCase());
        mChildMenu.add(getUserInfoList);
        mAllTestList.addAll(getUserInfoList);

        List<TestCase> messageList = new ArrayList<>();
        messageList.add(TestCaseV3Util.createBindPhoneCase(0));
        messageList.add(TestCaseV3Util.createBindPhoneCase(1));
        messageList.add(TestCaseV3Util.createBindPhoneCase(2));
        messageList.add(TestCaseV3Util.createBindPhoneCase(3));
        messageList.add(TestCaseV3Util.createUnbindPhoneCase());
        messageList.add(TestCaseV3Util.createUpdatePhoneCase(0));
        messageList.add(TestCaseV3Util.createBindEmailCase(0));
        messageList.add(TestCaseV3Util.createBindEmailCase(1));
        messageList.add(TestCaseV3Util.createBindEmailCase(2));
        messageList.add(TestCaseV3Util.createBindEmailCase(3));
        messageList.add(TestCaseV3Util.createUnbindEmailCase());
        messageList.add(TestCaseV3Util.createUpdateEmailCase(0));
        messageList.add(TestCaseV3Util.createResetPasswordByPhoneCodeCase(0));
        messageList.add(TestCaseV3Util.createResetPasswordByPhoneCodeCase(1));
        messageList.add(TestCaseV3Util.createResetPasswordByPhoneCodeCase(2));
        messageList.add(TestCaseV3Util.createResetPasswordByPhoneCodeCase(3));
        messageList.add(TestCaseV3Util.createResetPasswordByEmailCodeCase(0));
        messageList.add(TestCaseV3Util.createResetPasswordByEmailCodeCase(1));
        messageList.add(TestCaseV3Util.createResetPasswordByEmailCodeCase(2));
        messageList.add(TestCaseV3Util.createResetPasswordByEmailCodeCase(3));
        messageList.add(TestCaseV3Util.createUpdatePassword());
        messageList.add(TestCaseV3Util.createUpdateProfileCase(0));
        mChildMenu.add(messageList);
        mAllTestList.addAll(messageList);

        List<TestCase> mfaList = new ArrayList<>();
//        mfaList.add(TestCaseV3Util.createMfaCheckCase(0));
//        mfaList.add(TestCaseV3Util.createMfaCheckCase(1));
//        mfaList.add(TestCaseV3Util.createMfaCheckCase(2));
//        mfaList.add(TestCaseV3Util.createMfaVerifyByPhoneCase(0));
//        mfaList.add(TestCaseV3Util.createMfaVerifyByPhoneCase(1));
//        mfaList.add(TestCaseV3Util.createMfaVerifyByPhoneCase(2));
//        mfaList.add(TestCaseV3Util.createMfaVerifyByPhoneCase(3));
//        mfaList.add(TestCaseV3Util.createMfaVerifyByEmailCase(0));
//        mfaList.add(TestCaseV3Util.createMfaVerifyByEmailCase(1));
//        mfaList.add(TestCaseV3Util.createMfaVerifyByEmailCase(2));
//        mfaList.add(TestCaseV3Util.createMfaVerifyByOTPCase());
//        mfaList.add(TestCaseV3Util.createMfaVerifyByRecoveryCodeCase());

        mfaList.add(TestCaseV3Util.createSendBindMFARequestByPhoneCase());
        mfaList.add(TestCaseV3Util.createSendBindMFARequestByEmailCase());
        mfaList.add(TestCaseV3Util.createSendBindMFARequestByOtpCase());
        mfaList.add(TestCaseV3Util.createSendBindMFARequestByFaceCase());
        mfaList.add(TestCaseV3Util.createBindMFAByPhoneCase());
        mfaList.add(TestCaseV3Util.createBindMFAByEmailCase());
        mfaList.add(TestCaseV3Util.createBindMFAByOtpCase());
        mfaList.add(TestCaseV3Util.createBindMFAByFaceCase());
        mfaList.add(TestCaseV3Util.createUnBindMFACase(0));
        mfaList.add(TestCaseV3Util.createUnBindMFACase(1));
        mfaList.add(TestCaseV3Util.createUnBindMFACase(2));
        mfaList.add(TestCaseV3Util.createUnBindMFACase(3));
        mfaList.add(TestCaseV3Util.createGetAllBindingMFACase());
        mfaList.add(TestCaseV3Util.createGetBindingMFACase());
        mfaList.add(TestCaseV3Util.createGetUnBindingMFACase());
        mChildMenu.add(mfaList);
        mAllTestList.addAll(mfaList);

        List<TestCase> accountList = new ArrayList<>();
        accountList.add(TestCaseV3Util.createLogoutCase());
        accountList.add(TestCaseV3Util.createDeleteAccountCase(0));
        accountList.add(TestCaseV3Util.createDeleteAccountCase(1));
        accountList.add(TestCaseV3Util.createDeleteAccountCase(2));
        mChildMenu.add(accountList);
        mAllTestList.addAll(accountList);

        List<TestCase> tokenList = new ArrayList<>();
        tokenList.add(TestCaseV3Util.createGetNewAccessTokenByRefreshTokenCase());
        mChildMenu.add(tokenList);
        mAllTestList.addAll(tokenList);


        List<TestCase> bindingList = new ArrayList<>();
        bindingList.add(TestCaseV3Util.createAccountBindingCase(0));
        bindingList.add(TestCaseV3Util.createAccountBindingCase(1));
        bindingList.add(TestCaseV3Util.createAccountBindingCase(2));
        bindingList.add(TestCaseV3Util.createAccountBindingCase(3));
        bindingList.add(TestCaseV3Util.createAccountBindingCase(4));
        mChildMenu.add(bindingList);
        mAllTestList.addAll(bindingList);
    }

}
