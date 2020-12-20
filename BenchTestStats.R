setwd("/home/jean-yves/Documents/Ensai/3A/UE41-GenieLogiciel/") # to be customized
donnees <- read.csv(file = "BenchTestStats.csv",header=T)
summary(donnees$nbRows)
sd(donnees$nbRows)
summary(donnees$nbColumns)
sd(donnees$nbColumns)
summary(donnees$nbHeaders)
sd(donnees$nbHeaders)
summary(donnees$nbCells)
sd(donnees$nbCells)
sum(donnees$nbRows*donnees$nbColumns==donnees$nbCells)/dim(donnees)[1]
sum(donnees$nbRows*donnees$nbColumns>donnees$nbCells)/dim(donnees)[1]
sum(donnees$nbRows*donnees$nbColumns<donnees$nbCells)/dim(donnees)[1]
