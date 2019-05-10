package com.zhong.cardinals.mode;


/**
 * Created by zhong on 2017/12/5.
 */

public class DeviceInfo {//                     一加5                         vivo x5pro D

    private String imei;// 15位数字的设备id   865124038468734             '868299023077661'
    private String model;//设备型号          ONEPLUS A5000               'vivo X5Pro D'
    private String display;//               ONEPLUS A5000_23_171127     'LRX21M release-keys'
    private String device;//                OnePlus5                    'bbk6752_lwt_l'
    private String hardware;//              qcom                        'mt6752'
    private String id;//                    'NMF26X'                    'LRX21M'
    private String tags;//                  'release-keys'              'release-keys'
    private String type;//                  'user'                      'user'
    private String board;//                 'msm8998'                   'unknown'
    private String brand;//手机品牌           'OnePlus'                   'vivo'
    private int sdk_int;//Android API版本    25                          21
    private String baseOs;//                ''                          ''
    private String codeName;//              'REL'                       'REL'
    private String incremental;//           '3'                         '1508300328'
    private String release;//Android版本     '7.1.1'                     '5.0.2'
    private String security_patch;//安全补丁更新日期'2017-09-01'                '2017-05-05'

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getDisplay() {
        return display;
    }

    public void setDisplay(String display) {
        this.display = display;
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    public String getHardware() {
        return hardware;
    }

    public void setHardware(String hardware) {
        this.hardware = hardware;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBoard() {
        return board;
    }

    public void setBoard(String board) {
        this.board = board;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getSdk_int() {
        return sdk_int;
    }

    public void setSdk_int(int sdk_int) {
        this.sdk_int = sdk_int;
    }

    public String getBaseOs() {
        return baseOs;
    }

    public void setBaseOs(String baseOs) {
        this.baseOs = baseOs;
    }

    public String getCodeName() {
        return codeName;
    }

    public void setCodeName(String codeName) {
        this.codeName = codeName;
    }

    public String getIncremental() {
        return incremental;
    }

    public void setIncremental(String incremental) {
        this.incremental = incremental;
    }

    public String getRelease() {
        return release;
    }

    public void setRelease(String release) {
        this.release = release;
    }

    public String getSecurity_patch() {
        return security_patch;
    }

    public void setSecurity_patch(String security_patch) {
        this.security_patch = security_patch;
    }

    @Override
    public String toString() {
        return "DeviceInfo{" +
                "imei='" + imei + '\'' +
                ", model='" + model + '\'' +
                ", display='" + display + '\'' +
                ", device='" + device + '\'' +
                ", hardware='" + hardware + '\'' +
                ", id='" + id + '\'' +
                ", tags='" + tags + '\'' +
                ", type='" + type + '\'' +
                ", board='" + board + '\'' +
                ", brand='" + brand + '\'' +
                ", sdk_int=" + sdk_int +
                ", baseOs='" + baseOs + '\'' +
                ", codeName='" + codeName + '\'' +
                ", incremental='" + incremental + '\'' +
                ", release='" + release + '\'' +
                ", security_patch='" + security_patch + '\'' +
                '}';
    }
}
