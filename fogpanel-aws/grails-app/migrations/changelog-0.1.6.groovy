databaseChangeLog = {

    changeSet(author: "santhosh", id: "changelog-0.1.6") {
        
        insert(tableName: "volume_type") {
            column(name: "id", valueNumeric: 1)
            column(name: "version", valueNumeric: 0)
            column(name: "name", value: "General Purpose(SSD)")
            column(name: "size_min", valueNumeric: 1)
            column(name: "size_max", valueNumeric: 1024)
            column(name: "iops_min", valueNumeric: 3)
            column(name: "iops_max", valueNumeric: 3000)
            column(name: "baseline_iops", valueNumeric: 3)
            column(name: "is_iops", valueBoolean: true)
            column(name: "deleted", valueBoolean: false)
        }
        
        insert(tableName: "volume_type") {
            column(name: "id", valueNumeric: 2)
            column(name: "version", valueNumeric: 0)
            column(name: "name", value: "Provisioned Iops(SSD)")
            column(name: "size_min", valueNumeric: 10)
            column(name: "size_max", valueNumeric: 1024)
            column(name: "iops_min", valueNumeric: 100)
            column(name: "iops_max", valueNumeric: 4000)
            column(name: "baseline_iops", valueNumeric: null)
            column(name: "is_iops", valueBoolean: true)
            column(name: "deleted", valueBoolean: false)
        }
        
        insert(tableName: "volume_type") {
            column(name: "id", valueNumeric: 3)
            column(name: "version", valueNumeric: 0)
            column(name: "name", value: "Magnetic")
            column(name: "size_min", valueNumeric: 1)
            column(name: "size_max", valueNumeric: 1024)
            column(name: "iops_min", valueNumeric: null)
            column(name: "iops_max", valueNumeric: null)
            column(name: "baseline_iops", valueNumeric: null)
            column(name: "is_iops", valueBoolean: false)
            column(name: "deleted", valueBoolean: false)
        }
        
    }
}
