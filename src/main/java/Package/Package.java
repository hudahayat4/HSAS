package Package;

import java.io.InputStream;

public class Package {
    private int packageID; // or Integer
    private String packageName;
    private InputStream packagePic;
    private double packagePrice;
    private String isbfrReq;
    private String isExist;

    // Getters & Setters
    public Package() {}
    public int getPackageID() { return packageID; }
    public void setPackageID(int packageID) { this.packageID = packageID; }

    public String getPackageName() { return packageName; }
    public void setPackageName(String packageName) { this.packageName = packageName; }

    public InputStream getPackagePic() { return packagePic; }
    public void setPackagePic(InputStream packagePic) { this.packagePic = packagePic; }

    public double getPackagePrice() { return packagePrice; }
    public void setPackagePrice(double packagePrice) { this.packagePrice = packagePrice; }

    public String getIsbfrReq() { return isbfrReq; }
    public void setIsbfrReq(String isbfrReq) { this.isbfrReq = isbfrReq; }

    public String getIsExist() { return isExist; }
    public void setIsExist(String isExist) { this.isExist = isExist; }
}
