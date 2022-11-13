CREATE TABLE `admin` (
  `adminId` int,
  `adminUsername` varchar(20),
  `adminPassword` varchar(20)
);

CREATE TABLE `user` (
  `userLoginId` int,
  `userName` varchar(20),
  `password` varchar(20),
  `firstName` varchar(20),
  `lastName` varchar(20),
  `contact` bigint,
  `email` varchar(20),
  `reservationId` int
);

CREATE TABLE `reservation` (
  `reservationId` int,
  `reservationStatus` varchar(20),
  `reservationType` varchar(20),
  `reservationDate` LocalDate,
  `reservationTime` LocalTime,
  `source` varchar(20),
  `destination` varchar(20),
  `busId` int
);

CREATE TABLE `bus` (
  `busId` int,
  `busName` varchar(20),
  `driverName` varchar(20),
  `busType` varchar(20),
  `seats` int,
  `availableSeats` int,
  `arrivalTime` LocalTime,
  `departureTime` LocalTime,
  `route` int
);

CREATE TABLE `feedback` (
  `feedbackId` int,
  `driverRating` int,
  `serviceRating` int,
  `overallRating` int,
  `comments` varchar(50),
  `feedbackDate` LocalDate,
  `userId` int,
  `reservationId` int
);

CREATE TABLE `route` (
  `bus` list,
  `routeId` int,
  `routeFrom` int,
  `routeTo` int,
  `distance` int
);

ALTER TABLE `reservation` ADD FOREIGN KEY (`reservationId`) REFERENCES `user` (`reservationId`);

ALTER TABLE `bus` ADD FOREIGN KEY (`busId`) REFERENCES `reservation` (`busId`);

ALTER TABLE `route` ADD FOREIGN KEY (`routeId`) REFERENCES `bus` (`route`);

ALTER TABLE `feedback` ADD FOREIGN KEY (`userId`) REFERENCES `user` (`userLoginId`);

ALTER TABLE `reservation` ADD FOREIGN KEY (`reservationId`) REFERENCES `feedback` (`reservationId`);

ALTER TABLE `route` ADD FOREIGN KEY (`bus`) REFERENCES `bus` (`busId`);
