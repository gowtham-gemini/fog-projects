databaseChangeLog = {

	changeSet(author: "Nandhini (generated)", id: "1401777234710-1") {
            insert(tableName: "config") {
                column(name: "id", valueNumeric: 86)
                column(name: "version", valueNumeric: 1)
                column(name: "description", value: "organisation.background.img.url")
                column(name: "name", value: "organisation.background.img.url")
                column(name: "value", value: "")
            }
        }
    }
