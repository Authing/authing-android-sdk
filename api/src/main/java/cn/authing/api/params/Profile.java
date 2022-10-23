package cn.authing.api.params;

import org.json.JSONException;
import org.json.JSONObject;

import cn.authing.api.params.type.Gender;

/**
 * 用户资料
 */
public class Profile {

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 用户名
     */
    private String username;

    /**
     * 公司
     */
    private String company;

    /**
     * 头像
     */
    private String photo;

    /**
     * 设备
     */
    private String device;

    /**
     * 浏览器
     */
    private String browser;

    /**
     * 名称
     */
    private String name;

    /**
     * 名
     */
    private String givenName;

    /**
     * 姓
     */
    private String familyName;

    /**
     * 中间名
     */
    private String middleName;

    /**
     * 资料
     */
    private String profile;

    /**
     * 希望称呼的用户
     */
    private String preferredUsername;

    /**
     * 网站
     */
    private String website;

    /**
     * 性别 F : 女性; M : 男性
     * Enum: "M" "F" "U"
     */
    private Gender gender;

    /**
     * 生日
     */
    private String birthdate;

    /**
     * 地区
     */
    private String zoneinfo;

    /**
     * 语言地区
     */
    private String locale;

    /**
     * 地址
     */
    private String address;

    /**
     * 格式
     */
    private String formatted;

    /**
     * 街道地址
     */
    private String streetAddress;

    /**
     * 位置
     */
    private String locality;

    /**
     * 地区
     */
    private String region;

    /**
     * 邮政编码
     */
    private String postalCode;

    /**
     * 国家
     */
    private String country;

    /**
     * 省份
     */
    private String province;

    /**
     * 城市
     */
    private String city;

    /**
     * 用户自定义字段
     */
    private Object customData;

    /**
     * 第三方外部 ID
     */
    private String externalId;

    public Profile() {
        gender = Gender.U;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    public String getBrowser() {
        return browser;
    }

    public void setBrowser(String browser) {
        this.browser = browser;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGivenName() {
        return givenName;
    }

    public void setGivenName(String givenName) {
        this.givenName = givenName;
    }

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public String getPreferredUsername() {
        return preferredUsername;
    }

    public void setPreferredUsername(String preferredUsername) {
        this.preferredUsername = preferredUsername;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public String getZoneinfo() {
        return zoneinfo;
    }

    public void setZoneinfo(String zoneinfo) {
        this.zoneinfo = zoneinfo;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getFormatted() {
        return formatted;
    }

    public void setFormatted(String formatted) {
        this.formatted = formatted;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public String getLocality() {
        return locality;
    }

    public void setLocality(String locality) {
        this.locality = locality;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Object getCustomData() {
        return customData;
    }

    public void setCustomData(Object customData) {
        this.customData = customData;
    }

    public String getExternalId() {
        return externalId;
    }

    public void setExternalId(String externalId) {
        this.externalId = externalId;
    }

    public JSONObject toJSON() {
        JSONObject jsonObject = new JSONObject();
        try {
            if (getNickname() != null) {
                jsonObject.put("nickname", getNickname());
            }
            if (getUsername() != null) {
                jsonObject.put("username", getUsername());
            }
            if (getCompany() != null) {
                jsonObject.put("company", getCompany());
            }
            if (getPhoto() != null) {
                jsonObject.put("photo", getPhoto());
            }
            if (getDevice() != null) {
                jsonObject.put("device", getDevice());
            }
            if (getBrowser() != null) {
                jsonObject.put("browser", getBrowser());
            }
            if (getName() != null) {
                jsonObject.put("name", getName());
            }
            if (getGivenName() != null) {
                jsonObject.put("givenName", getGivenName());
            }
            if (getFamilyName() != null) {
                jsonObject.put("familyName", getFamilyName());
            }
            if (getMiddleName() != null) {
                jsonObject.put("middleName", getMiddleName());
            }
            if (getProfile() != null) {
                jsonObject.put("profile", getProfile());
            }
            if (getPreferredUsername() != null) {
                jsonObject.put("preferredUsername", getPreferredUsername());
            }
            if (getWebsite() != null) {
                jsonObject.put("website", getWebsite());
            }
            if (getGender() != null) {
                jsonObject.put("gender", getGender().toString());
            }
            if (getBirthdate() != null) {
                jsonObject.put("birthdate", getBirthdate());
            }
            if (getZoneinfo() != null) {
                jsonObject.put("zoneinfo", getZoneinfo());
            }
            if (getLocale() != null) {
                jsonObject.put("locale", getLocale());
            }
            if (getAddress() != null) {
                jsonObject.put("address", getAddress());
            }
            if (getFormatted() != null) {
                jsonObject.put("formatted", getFormatted());
            }
            if (getStreetAddress() != null) {
                jsonObject.put("streetAddress", getStreetAddress());
            }
            if (getLocality() != null) {
                jsonObject.put("locality", getLocality());
            }
            if (getRegion() != null) {
                jsonObject.put("region", getRegion());
            }
            if (getPostalCode() != null) {
                jsonObject.put("postalCode", getPostalCode());
            }
            if (getCountry() != null) {
                jsonObject.put("country", getCountry());
            }
            if (getProvince() != null) {
                jsonObject.put("province", getProvince());
            }
            if (getCity() != null) {
                jsonObject.put("city", getCity());
            }
            if (getCustomData() != null) {
                jsonObject.put("customData", getCustomData());
            }
            if (getExternalId() != null) {
                jsonObject.put("externalId", getExternalId());
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }
}
