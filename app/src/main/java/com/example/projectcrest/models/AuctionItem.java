import java.util.Collection;
import java.util.Date;

public class AuctionItem {
    private long id;
    private String name;
    private String description;
    private double startPrice;
    private Date startTime;
    private Date endTime;
    private User owner;
    private Date createDate;
    private AuctionPhoto auctionPhotos;
    private Collection<UserBid> userBids;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getStartPrice() {
        return startPrice;
    }

    public void setStartPrice(double startPrice) {
        this.startPrice = startPrice;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public AuctionPhoto getAuctionPhotos() {
        return auctionPhotos;
    }

    public void setAuctionPhotos(AuctionPhoto auctionPhotos) {
        this.auctionPhotos = auctionPhotos;
    }

    public Collection<UserBid> getUserBids() {
        return userBids;
    }

    public void setUserBids(Collection<UserBid> userBids) {
        this.userBids = userBids;
    }

    public AuctionItem(long id, String name, String description, double startPrice, Date startTime, Date endTime, User owner, Date createDate, AuctionPhoto auctionPhotos, Collection<UserBid> userBids) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.startPrice = startPrice;
        this.startTime = startTime;
        this.endTime = endTime;
        this.owner = owner;
        this.createDate = createDate;
        this.auctionPhotos = auctionPhotos;
        this.userBids = userBids;
    }
}
