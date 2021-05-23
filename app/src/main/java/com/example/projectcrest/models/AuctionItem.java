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
    private Collection<AuctionPhoto> auctionPhotos;
    private Collection<UserBid> userBids;
}
