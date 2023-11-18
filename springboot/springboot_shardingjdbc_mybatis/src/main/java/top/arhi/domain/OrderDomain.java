/*
* 永旺数字科技有限公司版权所有.
*/

package top.arhi.domain;

/**
 * (与数据库表字段一一对应的实体类,公有字段继承至父类)
 *
 * @author 今天例外
 * @version 1.0.0
 * @date 2021-04-07
 */
public class OrderDomain {
    private static final long serialVersionUID = 1L;
    /**
     * 主键ID
     */
    private Long Id;

    private Long shardingDbId;

    private Long shardingTableId;

    public OrderDomain(Long shardingDbId, Long shardingTableId) {
        this.shardingDbId = shardingDbId;
        this.shardingTableId = shardingTableId;
    }

    public OrderDomain() {
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getShardingDbId() {
        return shardingDbId;
    }

    public void setShardingDbId(Long shardingDbId) {
        this.shardingDbId = shardingDbId;
    }

    public Long getShardingTableId() {
        return shardingTableId;
    }

    public void setShardingTableId(Long shardingTableId) {
        this.shardingTableId = shardingTableId;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }
}