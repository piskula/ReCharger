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
    id                 BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    uuid               BINARY(16)      NOT NULL,
    account_id         BIGINT UNSIGNED NOT NULL,
    name               VARCHAR(127)    NOT NULL,
    mileage_initial    INT UNSIGNED    NOT NULL,
    percentage_initial INT UNSIGNED    NOT NULL,
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
    id               BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    uuid             BINARY(16)      NOT NULL,
    vehicle_id       BIGINT UNSIGNED NOT NULL,
    time             DATETIME(3)     NOT NULL,
    mileage          INT             NOT NULL,
    percentage_spent TINYINT         NOT NULL,
    percentage_from  TINYINT         NOT NULL,
    percentage_to    TINYINT         NOT NULL,
    price            DECIMAL(11, 2)  NOT NULL,
    kwh              DECIMAL(8, 2)     DEFAULT NULL,
    current_type     ENUM ('AC', 'DC') DEFAULT NULL,
    provider_id      BIGINT UNSIGNED   DEFAULT NULL,
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

INSERT INTO vehicle (uuid, account_id, name, mileage_initial, percentage_initial)
VALUES ((UUID_TO_BIN('2fe819e4-cad6-4406-a3f6-08a664460ffb')), 1, 'My EV6', 27376, 25);

INSERT INTO provider (uuid, name)
VALUES ((UUID_TO_BIN('98c47db5-834f-4694-b1fb-a5d97b453f3d')), 'eJoin'),
       ((UUID_TO_BIN('1ca2ff9c-3ed3-43dc-bc4a-029391da9e30')), 'Billa'),
       ((UUID_TO_BIN('8720c521-1f79-4729-a236-cf288f111584')), 'ZSE'),
       ((UUID_TO_BIN('3ecb02ca-e775-4291-bc60-65bca31dc9d6')), 'Kaufland'),
       ((UUID_TO_BIN('ffa2a421-582c-4b8d-aebe-a461e60ff4f6')), 'LIDL'),
       ((UUID_TO_BIN('f81b26e0-ea3d-49d7-8324-6dc238884a74')), 'U našich'),
       ((UUID_TO_BIN('82607977-dc74-4f82-96bf-8f73abc85c79')), 'Lukáš'),
       ((UUID_TO_BIN('c43fa12b-5f61-4fb5-92f0-1a94b42aa31f')), 'Greenway');

INSERT INTO charging (uuid, vehicle_id, time, mileage, percentage_spent, percentage_from, percentage_to, price, kwh, current_type, provider_id)
VALUES ((UUID_TO_BIN('320f0294-3a0a-4a04-afbe-123078da99c8')), 1, '2023-09-20 14:15:00', 27397, 4, 21, 80, 17.92, 45.96, 'DC', 1),
       ((UUID_TO_BIN('f4d9aacb-1751-4bda-ac25-ed0c8685a010')), 1, '2023-09-20 16:20:00', 27400, 0, 80, 89, 0.00, NULL, NULL, 2),
       ((UUID_TO_BIN('76fdeecb-5202-4224-bbd7-de9a966bacf4')), 1, '2023-09-22 18:41:00', 27613, 49, 40, 56, 0.00, NULL, NULL, 2),
       ((UUID_TO_BIN('c710fd36-d90a-4804-a79e-7e6acac1df65')), 1, '2023-09-25 18:41:00', 27671, 16, 40, 59, 0.00, NULL, NULL, 2),
       ((UUID_TO_BIN('e0c4b8bb-cb2c-487c-8db6-d751dc04ef36')), 1, '2023-09-27 16:12:00', 27765, 19, 40, 70, 0.00, NULL, NULL, 4),
       ((UUID_TO_BIN('7d4834b3-a3a1-404e-b211-482fcb891d9a')), 1, '2023-09-27 18:32:00', 27791, 4, 66, 79, 0.00, NULL, NULL, 2),
       ((UUID_TO_BIN('4c0a4af2-202e-4d39-a418-aa3858ba608e')), 1, '2023-09-28 10:07:00', 27804, 2, 77, 85, 1.98, 6.83, 'AC', 1),
       ((UUID_TO_BIN('63f1a79c-0969-459c-8d93-0e8a1c335d5a')), 1, '2023-09-28 18:04:00', 28001, 64, 21, 59, 9.12, 31.46, 'AC', 1),
       ((UUID_TO_BIN('aa29c692-fea0-46ad-836f-717a6a31c39a')), 1, '2023-09-30 09:32:00', 28017, 5, 54, 61, 1.45, 6.03, 'AC', 5),
       ((UUID_TO_BIN('ac5c5cc0-27c3-4643-8105-b56d13249f8a')), 1, '2023-09-30 16:47:00', 28020, 1, 60, 92, 7.80, 26.91, 'AC', 1),
       ((UUID_TO_BIN('f89fcdbe-42f6-4a50-8213-e1cba494c63a')), 1, '2023-10-01 18:04:00', 28234, 64, 28, 59, 0.00, NULL, NULL, 4),
       ((UUID_TO_BIN('a1d0dff1-beac-4942-b84b-a1626d42a7ee')), 1, '2023-10-02 10:05:00', 28278, 10, 49, 61, 0.00, NULL, NULL, 2),
       ((UUID_TO_BIN('bdd9ca11-07df-47d3-81b7-31a0629a28ae')), 1, '2023-10-04 09:12:00', 28348, 16, 45, 73, 0.00, 23.60, 'DC', 4),
       ((UUID_TO_BIN('669a8833-5acd-4147-9d45-dabc422d3b34')), 1, '2023-10-04 18:35:00', 28387, 7, 66, 84, 0.00, NULL, NULL, 4),
       ((UUID_TO_BIN('fcfeeaf5-96e8-4f2c-adf3-288fc200b102')), 1, '2023-10-06 07:30:00', 28419, 7, 49, 61, 0.00, NULL, NULL, 2),
       ((UUID_TO_BIN('9da65991-6180-4b6a-be69-774f5f1c7b24')), 1, '2023-10-07 03:15:00', 28624, 64, 25, 80, 5.05, 45.93, 'AC', 6),
       ((UUID_TO_BIN('3a0aa57f-6909-4eae-960f-58509c8093fa')), 1, '2023-10-08 11:50:00', 28635, 4, 76, 94, 3.06, 16.10, 'AC', 6),
       ((UUID_TO_BIN('edf6c4d6-0a51-4b00-aeb6-900bcf1ea6ef')), 1, '2023-10-09 07:25:00', 28877, 81, 13, 24, 0.00, 10.00, 'DC', 4),
       ((UUID_TO_BIN('fd10dba6-ec82-407d-8cda-c18c8e0b1470')), 1, '2023-10-09 09:57:00', 28898, 4, 20, 44, 0.00, 19.00, 'DC', 4),
       ((UUID_TO_BIN('fd96d13b-2bc6-4344-86ed-fb5d1f228cc1')), 1, '2023-10-09 09:57:00', 28965, 18, 26, 68, 0.00, 33.46, 'AC', 7),
       ((UUID_TO_BIN('30f081ef-144d-46fb-a5a3-4feef46c6097')), 1, '2023-10-11 08:55:00', 28976, 1, 67, 85, 0.00, 16.69, 'DC', 4),
       ((UUID_TO_BIN('37d18886-1f11-4bba-94fa-ae28667b209c')), 1, '2023-10-11 18:30:00', 29130, 45, 40, 56, 0.00, NULL, NULL, 2),
       ((UUID_TO_BIN('fd8c9075-8932-4745-a39e-04103ccb339d')), 1, '2023-10-12 08:10:00', 29146, 3, 53, 67, 0.00, NULL, NULL, 2),
       ((UUID_TO_BIN('8a5db893-4379-4b9f-b329-9e57d314d638')), 1, '2023-10-12 16:45:00', 29169, 4, 63, 73, 0.00, NULL, NULL, 2),
       ((UUID_TO_BIN('74822326-6fa7-498d-a79c-a37725cf2661')), 1, '2023-10-14 09:05:00', 29218, 11, 62, 70, 0.00, NULL, NULL, 2),
       ((UUID_TO_BIN('b64c7c5b-e13d-407a-b9cd-9e1f77bad6df')), 1, '2023-10-14 09:22:00', 29233, 2, 68, 94, 6.68, 23.04, 'AC', 1),
       ((UUID_TO_BIN('d8d1d92b-2c02-44d2-ae24-0cb8dd898391')), 1, '2023-10-15 01:25:00', 29445, 60, 34, 68, 18.28, 30.97, 'DC', 3),
       ((UUID_TO_BIN('c2cb3499-15f9-4cb9-a9bf-c209b1fb7ff1')), 1, '2023-10-15 11:50:00', 29562, 44, 24, 47, 0.00, 19.76, 'DC', 4),
       ((UUID_TO_BIN('6a9570e3-25a0-4dd0-9775-f0bffb092acb')), 1, '2023-10-15 16:20:00', 29582, 4, 43, 74, 2.00, NULL, NULL, 2),
       ((UUID_TO_BIN('20c64ab4-eb3f-4af6-bc4f-cdf6dd8499d1')), 1, '2023-10-17 08:40:00', 29611, 6, 68, 92, 0.00, NULL, NULL, 4),
       ((UUID_TO_BIN('8417430c-3493-46fa-a607-ff2a2e538dc1')), 1, '2023-10-18 02:00:00', 29721, 32, 60, 79, 0.00, 19.77, 'DC', 4),
       ((UUID_TO_BIN('d9f5d138-28b6-4d31-a3cb-4e5c3f210c7c')), 1, '2023-10-18 08:30:00', 29862, 46, 33, 55, 0.00, 19.76, 'DC', 4),
       ((UUID_TO_BIN('06c412d6-701e-4e5e-a28b-f8c5830a24ba')), 1, '2023-10-18 15:00:00', 29880, 5, 50, 83, 2.00, NULL, NULL, 2),
       ((UUID_TO_BIN('a6d40ffe-99b4-44bc-9b9d-8ac6d5962180')), 1, '2023-10-19 08:40:00', 29915, 8, 75, 84, 2.14, 7.39, 'AC', 1),
       ((UUID_TO_BIN('1e929bb5-faa1-4eb0-8924-8a4123d6fb4a')), 1, '2023-10-19 16:32:00', 30111, 80, 4, 33, 3.86, 22.72, 'AC', 6),
       ((UUID_TO_BIN('fe6a7a23-1a5e-402c-b41a-30d3cea55041')), 1, '2023-10-20 02:00:00', 30147, 12, 21, 100, 7.18, 65.24, 'AC', 6),
       ((UUID_TO_BIN('2104762a-9d0e-4b09-ba0a-2e182cfd9d5f')), 1, '2023-10-20 15:20:00', 30186, 12, 88, 98, 1.40, NULL, NULL, 6),
       ((UUID_TO_BIN('531f5fb6-692b-4afa-8fef-96bda008f981')), 1, '2023-10-20 21:00:00', 30364, 58, 40, 100, 5.77, 52.44, 'AC', 6),
       ((UUID_TO_BIN('d2818ef6-87e6-4a50-acca-7e6932a70114')), 1, '2023-10-21 01:19:00', 30589, 69, 31, 42, 1.84, 9.71, 'AC', NULL),
       ((UUID_TO_BIN('e304d9d8-dc7b-41e8-9626-ca9c8d9c21f5')), 1, '2023-10-21 13:15:00', 30592, 1, 41, 90, 28.25, 40.96, 'DC', 8),
       ((UUID_TO_BIN('ff4b6eb1-c34b-49ab-8cab-8969197bfaa8')), 1, '2023-10-21 16:40:00', 30596, 1, 89, 95, 1.06, 5.32, 'AC', NULL),
       ((UUID_TO_BIN('cc298fde-38f0-4b9d-a201-def362ff5213')), 1, '2023-10-21 22:17:00', 30827, 73, 22, 100, 7.04, 64.00, 'AC', 6),
       ((UUID_TO_BIN('138f8b5a-8553-4e6f-a38f-25a59a0b4b61')), 1, '2023-10-22 09:00:00', 30838, 3, 97, 100, 0.40, 2.35, 'AC', 6),
       ((UUID_TO_BIN('19981c91-853d-4aea-b3e8-64299669fcae')), 1, '2023-10-22 16:30:00', 31025, 63, 37, 86, 4.00, NULL, NULL, 2),
       ((UUID_TO_BIN('57f7d7a4-1f6c-457d-a34e-2a68015a3176')), 1, '2023-10-26 07:45:00', 31191, 46, 40, 55, 4.00, NULL, NULL, 2),
       ((UUID_TO_BIN('b1d473c4-dd08-4324-9ba9-0840a3faf091')), 1, '2023-10-26 20:30:00', 31232, 11, 44, 60, 0.00, 13.01, 'AC', NULL),
       ((UUID_TO_BIN('5436e032-ac43-4061-859b-dac0513dc360')), 1, '2023-10-27 14:15:00', 31259, 6, 54, 58, 0.00, 3.10, 'AC', 4),
       ((UUID_TO_BIN('3b0686a3-f940-4eec-a298-5e575f24a617')), 1, '2023-10-28 08:10:00', 31298, 10, 48, 70, 0.00, 19.64, 'DC', 4),
       ((UUID_TO_BIN('80a53a07-f405-4525-8c76-15c2195813f7')), 1, '2023-10-28 08:45:00', 31299, 0, 70, 85, 0.00, NULL, NULL, 2),
       ((UUID_TO_BIN('e11de16e-d5f1-4172-b803-7c5275242ce1')), 1, '2023-11-01 07:20:00', 31410, 31, 54, 68, 0.00, NULL, NULL, 2),
       ((UUID_TO_BIN('28bd6454-5738-4a7e-a899-be0e320bba11')), 1, '2023-11-01 15:35:00', 31426, 3, 65, 69, 1.45, 3.70, 'AC', 3),
       ((UUID_TO_BIN('8d429839-3686-4277-8a23-b94115006b59')), 1, '2023-11-02 09:35:00', 31531, 30, 39, 67, 7.19, 24.79, 'AC', 1),
       ((UUID_TO_BIN('abeb2c49-7de0-410b-b885-2311fcc8d828')), 1, '2023-11-02 11:20:00', 31531, 0, 67, 92, 7.49, 19.21, 'DC', 1),
       ((UUID_TO_BIN('74435efb-7099-4c7c-832b-6995fc7fd2c5')), 1, '2023-11-03 12:10:00', 31736, 77, 15, 80, 5.80, 52.77, 'AC', 6),
       ((UUID_TO_BIN('61f69544-2c64-44b5-a4cb-be177322bd4f')), 1, '2023-11-05 01:19:00', 31873, 45, 35, 100, 5.80, 52.71, 'AC', 6),
       ((UUID_TO_BIN('8ba03db3-cdce-4c13-b5d0-ed029aeabba3')), 1, '2023-11-05 09:30:00', 31878, 2, 98, 100, 0.29, 1.66, 'AC', 6),
       ((UUID_TO_BIN('d58b81b3-e1fb-4dac-9d3f-6440c533eda8')), 1, '2023-11-05 15:45:00', 32072, 61, 39, 59, 4.00, NULL, NULL, 2),
       ((UUID_TO_BIN('346b20b4-48bd-481c-898e-99a270841d0d')), 1, '2023-11-06 08:05:00', 32125, 12, 47, 54, 0.00, 6.20, 'AC', 4),
       ((UUID_TO_BIN('1c1328a6-2673-4ecf-a0b6-abeae550a651')), 1, '2023-11-08 10:00:00', 32194, 18, 36, 68, 0.00, NULL, NULL, 4),
       ((UUID_TO_BIN('0eb4580c-f07e-4242-9549-c612e677b8ec')), 1, '2023-11-10 07:45:00', 32339, 36, 32, 44, 0.00, NULL, NULL, 2),
       ((UUID_TO_BIN('fe8afe24-cd46-40b3-b867-d6706bf0a506')), 1, '2023-11-11 14:45:00', 32432, 29, 15, 94, 0.00, 62.97, 'DC', 3),
       ((UUID_TO_BIN('96b13c50-0f83-47b8-acf1-9cdcbde79726')), 1, '2023-11-12 16:30:00', 32519, 17, 77, 84, 0.00, NULL, NULL, NULL),
       ((UUID_TO_BIN('33f157c0-d256-4907-821c-955f6a8fa193')), 1, '2023-11-15 09:45:00', 32592, 20, 64, 86, 0.00, NULL, NULL, 4),
       ((UUID_TO_BIN('89d59a36-4874-4a82-8c55-30db773744c1')), 1, '2023-11-17 08:15:00', 32663, 16, 70, 96, 0.00, NULL, NULL, 4),
       ((UUID_TO_BIN('20bcebef-4438-4109-9fb6-2114e8242390')), 1, '2023-11-17 23:00:00', 32866, 75, 21, 36, 1.38, 12.49, 'AC', 6),
       ((UUID_TO_BIN('0ab4e7e8-c42d-46c8-8602-4aaaead25a25')), 1, '2023-11-18 23:00:00', 32898, 13, 23, 39, 1.38, 12.55, 'AC', 6),
       ((UUID_TO_BIN('90392adb-aa01-4895-b94a-75d69996408b')), 1, '2023-11-19 07:00:00', 32898, 0, 39, 46, 1.00, NULL, NULL, 6),
       ((UUID_TO_BIN('644c8782-bd9e-403c-8099-5d774331b618')), 1, '2023-11-19 08:45:00', 32904, 2, 44, 100, 7.90, 46.05, 'AC', 6),
       ((UUID_TO_BIN('42556b26-2545-4555-9ce9-6f4d991d2193')), 1, '2023-11-19 16:00:00', 33110, 63, 37, 42, 0.00, NULL, NULL, 4),
       ((UUID_TO_BIN('a34b9077-3a34-4819-89dc-ed61ebb907bd')), 1, '2023-11-19 16:00:00', 33196, 25, 17, 40, 0.00, 19.77, 'DC', 4),
       ((UUID_TO_BIN('ad952dad-a959-4180-9cd0-41a8645d629c')), 1, '2023-11-19 16:00:00', 33200, 1, 39, 58, 0.00, 15.44, 'AC', NULL),
       ((UUID_TO_BIN('8409c7a0-812d-4a04-b7ea-95f071c61db4')), 1, '2023-11-19 16:00:00', 33270, 19, 39, 77, 3.00, 34.50, 'DC', NULL),
       ((UUID_TO_BIN('0c0fd452-e29d-4be6-9f27-9a704c9a8685')), 1, '2023-11-19 16:00:00', 33359, 22, 55, 67, 0.00, NULL, NULL, 4),
       ((UUID_TO_BIN('8aa9966f-1d82-43d1-8e44-1619dfb3534e')), 1, '2023-11-19 16:00:00', 33422, 19, 48, 70, 0.00, NULL, NULL, NULL),
       ((UUID_TO_BIN('ea50a559-aeab-4596-ad62-4b368347c0e9')), 1, '2023-11-19 16:00:00', 33501, 21, 49, 51, 0.00, NULL, NULL, 4),
       ((UUID_TO_BIN('2cb66056-499f-4b1b-832a-d23e50725901')), 1, '2023-11-19 16:00:00', 33520, 6, 45, 48, 1.48, 3.80, 'DC', 1),
       ((UUID_TO_BIN('75f5ca4e-63ea-4a9b-ad96-ed98b5793648')), 1, '2023-11-19 16:00:00', 33533, 4, 44, 91, 20.33, 41.49, 'DC', 3),
       ((UUID_TO_BIN('b31fafbe-2345-4a00-8b54-7afdef97df48')), 1, '2023-11-19 16:00:00', 33548, 3, 88, 99, 4.32, 11.10, 'AC', 3),
       ((UUID_TO_BIN('dad1c7e9-e36d-47a8-a728-d9f7fbf3dc1c')), 1, '2023-11-19 16:00:00', 33752, 83, 16, 80, 5.69, 51.72, 'AC', 6),
       ((UUID_TO_BIN('0c4a3be2-41b3-43ff-88bd-81d91c43076c')), 1, '2023-11-19 16:00:00', 33760, 7, 73, 100, 2.67, 24.27, 'AC', 6),
       ((UUID_TO_BIN('18d0166a-0ed7-4873-9697-0d5865712b5b')), 1, '2023-11-19 16:00:00', 33965, 90, 10, 71, 0.00, 50.81, 'DC', 3);
#        ((UUID_TO_BIN('')), 1, '2023-11-19 16:00:00', 34053, 20, 51, , 0.00, NULL, NULL, 4),
