CREATE TABLE vehicle
(
    id      BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    uuid    BINARY(16)   NOT NULL,
    name    VARCHAR(127) NOT NULL,
    mileage INT          NOT NULL
);

CREATE TABLE provider
(
    id   BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    uuid BINARY(16)   NOT NULL,
    name VARCHAR(127) NOT NULL
);

CREATE TABLE charging
(
    id              BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    uuid            BINARY(16)      NOT NULL,
    vehicle_id      BIGINT UNSIGNED NOT NULL,
    time            DATETIME(3)     NOT NULL,
    mileage         INT             NOT NULL,
    percentage_from TINYINT         NOT NULL,
    percentage_to   TINYINT         NOT NULL,
    price           DECIMAL(11, 2)  NOT NULL,
    kwh             DECIMAL(8, 2)   DEFAULT NULL,
    provider_id     BIGINT UNSIGNED DEFAULT NULL,
    CONSTRAINT fk_charging_to_vehicle
        FOREIGN KEY (vehicle_id) REFERENCES vehicle (id)
            ON DELETE CASCADE
            ON UPDATE RESTRICT,
    CONSTRAINT fk_charging_to_provider
        FOREIGN KEY (provider_id) REFERENCES provider (id)
            ON DELETE SET NULL
            ON UPDATE RESTRICT
);

INSERT INTO vehicle (uuid, name, mileage)
VALUES ((UUID_TO_BIN('2fe819e4-cad6-4406-a3f6-08a664460ffb')), 'My EV6', 27376);

INSERT INTO provider (uuid, name)
VALUES ((UUID_TO_BIN('98c47db5-834f-4694-b1fb-a5d97b453f3d')), 'eJoin'),
       ((UUID_TO_BIN('1ca2ff9c-3ed3-43dc-bc4a-029391da9e30')), 'Billa'),
       ((UUID_TO_BIN('8720c521-1f79-4729-a236-cf288f111584')), 'ZSE'),
       ((UUID_TO_BIN('3ecb02ca-e775-4291-bc60-65bca31dc9d6')), 'Kaufland'),
       ((UUID_TO_BIN('ffa2a421-582c-4b8d-aebe-a461e60ff4f6')), 'LIDL');

INSERT INTO charging (uuid, vehicle_id, time, mileage, percentage_from, percentage_to, price, kwh, provider_id)
VALUES ((UUID_TO_BIN('320f0294-3a0a-4a04-afbe-123078da99c8')), 1, '2023-09-20 14:15:00', 27397, 21, 80, 17.92, 45.96, 1),
       ((UUID_TO_BIN('f4d9aacb-1751-4bda-ac25-ed0c8685a010')), 1, '2023-09-20 16:20:00', 27400, 80, 89, 0.00, NULL, 2),
       ((UUID_TO_BIN('76fdeecb-5202-4224-bbd7-de9a966bacf4')), 1, '2023-09-22 18:41:00', 27613, 21, 80, 0.00, NULL, 2),
       ((UUID_TO_BIN('c710fd36-d90a-4804-a79e-7e6acac1df65')), 1, '2023-09-25 18:41:00', 27671, 40, 59, 0.00, NULL, 2),
       ((UUID_TO_BIN('e0c4b8bb-cb2c-487c-8db6-d751dc04ef36')), 1, '2023-09-27 16:12:00', 27765, 40, 70, 0.00, NULL, 4),
       ((UUID_TO_BIN('7d4834b3-a3a1-404e-b211-482fcb891d9a')), 1, '2023-09-27 18:32:00', 27791, 66, 79, 0.00, NULL, 2),
       ((UUID_TO_BIN('4c0a4af2-202e-4d39-a418-aa3858ba608e')), 1, '2023-09-28 10:07:00', 27804, 77, 85, 1.98, 6.83, 1),
       ((UUID_TO_BIN('63f1a79c-0969-459c-8d93-0e8a1c335d5a')), 1, '2023-09-28 18:04:00', 28001, 21, 59, 9.12, 31.46, 1),
       ((UUID_TO_BIN('aa29c692-fea0-46ad-836f-717a6a31c39a')), 1, '2023-09-30 9:32:00', 28017, 54, 61, 1.45, 6.03, 5),
       ((UUID_TO_BIN('ac5c5cc0-27c3-4643-8105-b56d13249f8a')), 1, '2023-09-30 16:47:00', 28020, 60, 92, 7.80, 26.91, 1),
       ((UUID_TO_BIN('f89fcdbe-42f6-4a50-8213-e1cba494c63a')), 1, '2023-10-01 18:04:00', 28234, 28, 59, 0.00, NULL, 4),
       ((UUID_TO_BIN('a1d0dff1-beac-4942-b84b-a1626d42a7ee')), 1, '2023-10-02 10:05:00', 28017, 49, 61, 0.00, NULL, 2);
