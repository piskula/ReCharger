CREATE TABLE account
(
    id                  BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    uuid                BINARY(16)   NOT NULL,
    provider            VARCHAR(255) NOT NULL,
    provider_identifier VARCHAR(255) NOT NULL,
    email               VARCHAR(255) NOT NULL
);

CREATE TABLE vehicle
(
    id         BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    uuid       BINARY(16)      NOT NULL,
    account_id BIGINT UNSIGNED NOT NULL,
    name       VARCHAR(127)    NOT NULL,
    mileage    INT             NOT NULL,
    CONSTRAINT fk_vehicle_to_account
        FOREIGN KEY (account_id) REFERENCES account (id)
            ON DELETE CASCADE
            ON UPDATE RESTRICT
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

INSERT INTO account (uuid, provider, provider_identifier, email)
VALUES ((UUID_TO_BIN('c911bfd7-0872-44b7-85c4-898399cf88d1')), 'accounts.google.com', '??', 'momosilabs@gmail.com');

INSERT INTO vehicle (uuid, account_id, name, mileage)
VALUES ((UUID_TO_BIN('2fe819e4-cad6-4406-a3f6-08a664460ffb')), 1, 'My EV6', 27376);

INSERT INTO provider (uuid, name)
VALUES ((UUID_TO_BIN('98c47db5-834f-4694-b1fb-a5d97b453f3d')), 'eJoin'),
       ((UUID_TO_BIN('1ca2ff9c-3ed3-43dc-bc4a-029391da9e30')), 'Billa'),
       ((UUID_TO_BIN('8720c521-1f79-4729-a236-cf288f111584')), 'ZSE'),
       ((UUID_TO_BIN('3ecb02ca-e775-4291-bc60-65bca31dc9d6')), 'Kaufland'),
       ((UUID_TO_BIN('ffa2a421-582c-4b8d-aebe-a461e60ff4f6')), 'LIDL'),
       ((UUID_TO_BIN('f81b26e0-ea3d-49d7-8324-6dc238884a74')), 'U našich'),
       ((UUID_TO_BIN('82607977-dc74-4f82-96bf-8f73abc85c79')), 'Lukáš');

INSERT INTO charging (uuid, vehicle_id, time, mileage, percentage_from, percentage_to, price, kwh, provider_id)
VALUES ((UUID_TO_BIN('320f0294-3a0a-4a04-afbe-123078da99c8')), 1, '2023-09-20 14:15:00', 27397, 21, 80, 17.92, 45.96, 1),
       ((UUID_TO_BIN('f4d9aacb-1751-4bda-ac25-ed0c8685a010')), 1, '2023-09-20 16:20:00', 27400, 80, 89, 0.00, NULL, 2),
       ((UUID_TO_BIN('76fdeecb-5202-4224-bbd7-de9a966bacf4')), 1, '2023-09-22 18:41:00', 27613, 21, 80, 0.00, NULL, 2),
       ((UUID_TO_BIN('c710fd36-d90a-4804-a79e-7e6acac1df65')), 1, '2023-09-25 18:41:00', 27671, 40, 59, 0.00, NULL, 2),
       ((UUID_TO_BIN('e0c4b8bb-cb2c-487c-8db6-d751dc04ef36')), 1, '2023-09-27 16:12:00', 27765, 40, 70, 0.00, NULL, 4),
       ((UUID_TO_BIN('7d4834b3-a3a1-404e-b211-482fcb891d9a')), 1, '2023-09-27 18:32:00', 27791, 66, 79, 0.00, NULL, 2),
       ((UUID_TO_BIN('4c0a4af2-202e-4d39-a418-aa3858ba608e')), 1, '2023-09-28 10:07:00', 27804, 77, 85, 1.98, 6.83, 1),
       ((UUID_TO_BIN('63f1a79c-0969-459c-8d93-0e8a1c335d5a')), 1, '2023-09-28 18:04:00', 28001, 21, 59, 9.12, 31.46, 1),
       ((UUID_TO_BIN('aa29c692-fea0-46ad-836f-717a6a31c39a')), 1, '2023-09-30 09:32:00', 28017, 54, 61, 1.45, 6.03, 5),
       ((UUID_TO_BIN('ac5c5cc0-27c3-4643-8105-b56d13249f8a')), 1, '2023-09-30 16:47:00', 28020, 60, 92, 7.80, 26.91, 1),
       ((UUID_TO_BIN('f89fcdbe-42f6-4a50-8213-e1cba494c63a')), 1, '2023-10-01 18:04:00', 28234, 28, 59, 0.00, NULL, 4),
       ((UUID_TO_BIN('a1d0dff1-beac-4942-b84b-a1626d42a7ee')), 1, '2023-10-02 10:05:00', 28278, 49, 61, 0.00, NULL, 2),
       ((UUID_TO_BIN('bdd9ca11-07df-47d3-81b7-31a0629a28ae')), 1, '2023-10-04 09:12:00', 28348, 45, 73, 0.00, 23.60, 4),
       ((UUID_TO_BIN('669a8833-5acd-4147-9d45-dabc422d3b34')), 1, '2023-10-04 18:35:00', 28387, 66, 84, 0.00, NULL, 4),
       ((UUID_TO_BIN('fcfeeaf5-96e8-4f2c-adf3-288fc200b102')), 1, '2023-10-06 07:30:00', 28419, 49, 61, 0.00, NULL, 2),
       ((UUID_TO_BIN('9da65991-6180-4b6a-be69-774f5f1c7b24')), 1, '2023-10-07 03:15:00', 28624, 25, 80, 5.05, 45.93, 6),
       ((UUID_TO_BIN('3a0aa57f-6909-4eae-960f-58509c8093fa')), 1, '2023-10-08 11:50:00', 28635, 76, 94, 3.06, 16.10, 6),
       ((UUID_TO_BIN('edf6c4d6-0a51-4b00-aeb6-900bcf1ea6ef')), 1, '2023-10-09 07:25:00', 28877, 13, 24, 0.00, 10.00, 4),
       ((UUID_TO_BIN('fd10dba6-ec82-407d-8cda-c18c8e0b1470')), 1, '2023-10-09 09:57:00', 28898, 20, 44, 0.00, 19.00, 4),
       ((UUID_TO_BIN('fd96d13b-2bc6-4344-86ed-fb5d1f228cc1')), 1, '2023-10-09 09:57:00', 28965, 26, 68, 0.00, 33.46, 7),
       ((UUID_TO_BIN('30f081ef-144d-46fb-a5a3-4feef46c6097')), 1, '2023-10-11 08:55:00', 28976, 67, 85, 0.00, 16.69, 4),
       ((UUID_TO_BIN('37d18886-1f11-4bba-94fa-ae28667b209c')), 1, '2023-10-11 18:30:00', 29130, 40, 56, 0.00, NULL, 2),
       ((UUID_TO_BIN('fd8c9075-8932-4745-a39e-04103ccb339d')), 1, '2023-10-12 08:10:00', 29146, 53, 67, 0.00, NULL, 2),
       ((UUID_TO_BIN('8a5db893-4379-4b9f-b329-9e57d314d638')), 1, '2023-10-12 16:45:00', 29169, 63, 73, 0.00, NULL, 2),
       ((UUID_TO_BIN('74822326-6fa7-498d-a79c-a37725cf2661')), 1, '2023-10-14 09:05:00', 29218, 62, 70, 0.00, NULL, 2),
       ((UUID_TO_BIN('b64c7c5b-e13d-407a-b9cd-9e1f77bad6df')), 1, '2023-10-14 09:22:00', 29233, 68, 94, 6.68, 23.04, 1),
       ((UUID_TO_BIN('d8d1d92b-2c02-44d2-ae24-0cb8dd898391')), 1, '2023-10-15 01:25:00', 29445, 34, 68, 18.28, 30.97, 3),
       ((UUID_TO_BIN('c2cb3499-15f9-4cb9-a9bf-c209b1fb7ff1')), 1, '2023-10-15 11:50:00', 29562, 24, 47, 0.00, 19.76, 4),
       ((UUID_TO_BIN('6a9570e3-25a0-4dd0-9775-f0bffb092acb')), 1, '2023-10-15 16:20:00', 29582, 43, 74, 2.00, NULL, 2),
       ((UUID_TO_BIN('20c64ab4-eb3f-4af6-bc4f-cdf6dd8499d1')), 1, '2023-10-17 08:40:00', 29611, 68, 92, 0.00, NULL, 4);
