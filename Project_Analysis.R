
case1 <- read.csv("D:\\DATA ANALYTICS\\ASSIGNMENT\\2nd sem\\pda\\ca1\\shared\\project\\satya\\output\\usecase1\\usecase1.csv")

case2 <- read.csv("D:\\DATA ANALYTICS\\ASSIGNMENT\\2nd sem\\pda\\ca1\\shared\\project\\satya\\output\\usecase22\\usecase22.csv")

case3 <- read.csv("D:\\DATA ANALYTICS\\ASSIGNMENT\\2nd sem\\pda\\ca1\\shared\\project\\satya\\output\\usecase3\\usecase3.csv")

case4 <- read.csv("D:\\DATA ANALYTICS\\ASSIGNMENT\\2nd sem\\pda\\ca1\\shared\\project\\satya\\output\\usecase4\\usecase4.csv")


library(ggplot2)
library(reshape)
theme_set(theme_classic())

## first graph
TopOrder <- order(case1$Avg_Price,decreasing = T)
TopOrder <- case1[TopOrder,]
TopOrder <- as.data.frame(TopOrder)
TopOrder <- TopOrder[1:10,]

ggplot(TopOrder,aes(x=District,y=Avg_Price))+geom_bar(stat="identity",width = 0.5, fill="tomato2")+theme(axis.text.x = element_text(angle=65, vjust=0.6))+geom_text(aes(label=paste(Avg_Price,"$")),vjust=-0.3, size=3.5)+labs(title="Top 10 average house price in districts of UK")
                                                                                                                                                                                                                    
## second graph
data.m <-melt(case2,id.vars="Tenure")
ggplot(data.m, aes(Tenure, value)) +   
  geom_bar(aes(fill = variable), position = "dodge", stat="identity")+theme(axis.text.x = element_text(vjust=0.3))+geom_text(aes(label=value),vjust=0.3,hjust=0.3,size=3.5)+labs(title="Number of sold houses based on Tenure")

## third graph
case3<-case3[!(case3$max >250000),]
case3<-case3[!(case3$min < 1000),]
Expensive <- order(case3$max,decreasing = T)
Expensive <- case3[Expensive,]
Expensive <- as.data.frame(Expensive)
Expensive <- Expensive[1:10,]


cheap <- order(case3$max)
cheap <- case3[cheap,]
cheap <- as.data.frame(cheap)
cheap <- cheap[1:10,]


ggplot(Expensive,aes(x=county,y=min,label=min,colour="min",group=1))+geom_line()+geom_text(vjust=0, colour="green")+geom_line(aes(y=max,colour="max"))+theme(axis.text.x = element_text(angle=65, vjust=0.6))+labs(title="Max and Min values of most expensive places")

ggplot(cheap,aes(x=county,y=max,label=max,colour="max",group=1))+geom_line()+geom_text(vjust=0, colour="red")+geom_line(aes(y=min,colour="min"))+theme(axis.text.x = element_text(angle=65, vjust=0.6))+labs(title="Max and Min values of least expensive places")

## fourth graph

ggplot(case4,aes(x=Property_Type,y=Count))+geom_point(size=5)+geom_segment(aes(x=Property_Type, 
                                                               xend=Property_Type, 
                                                               y=0, 
                                                               yend=Count)) + 
  labs(title="Sales according to Property Type") + 
  theme(axis.text.x = element_text(vjust=0.6))


hive1 <- read.csv("D:\\DATA ANALYTICS\\ASSIGNMENT\\2nd sem\\pda\\ca1\\shared\\project\\satya\\hive_output\\HiveTask_Output1\\hiveoutput1.csv")
hive2 <- read.csv("D:\\DATA ANALYTICS\\ASSIGNMENT\\2nd sem\\pda\\ca1\\shared\\project\\satya\\hive_output\\HiveTask_Output2\\hiveoutput2.csv")
pig <- read.csv("D:\\DATA ANALYTICS\\ASSIGNMENT\\2nd sem\\pda\\ca1\\shared\\project\\satya\\pig_output\\pigoutput.csv")

pig$X..ADUR.. <- gsub("[^a-zA-Z]","",pig$X..ADUR..)

hive1 <- hive1[!hive1$Property_type=="O",]
ggplot(hive1,aes(x=Property_type,y=Avg_price))+geom_bar(stat="identity",width = 0.5, fill="tomato2")+theme(axis.text.x = element_text(angle=65, vjust=0.6))+geom_text(aes(label=paste(Avg_price,"$")),vjust=0.3, size=3.5)+labs(title="Average house price according to house type")+coord_flip()



ggplot(hive2,aes(x=Duration,y=Max,group=1,colour="Max"))+geom_line()+geom_text(label=hive2$Max,vjust=0, colour="blue")

View(pig)
names(popularity)
popularity <- order(pig$X..ADUR..,decreasing = T)
popularity <- pig[popularity,]
popularity <- as.data.frame(popularity)
popularity <- popularity[1:10,]
View(popularity)
ggplot(popularity,aes(x=X..ADUR..,y=X257))+geom_bar(stat="identity",width = 0.5, fill="tomato")+theme(axis.text.x = element_text(angle=65, vjust=0.6))+geom_text(aes(label=X257),vjust=0.3, size=3.5)+labs(title="Most Popular Cities according to house purchase")+coord_flip()

