-- --------------------------------------------------------
-- 호스트:                          127.0.0.1
-- 서버 버전:                        8.0.34 - MySQL Community Server - GPL
-- 서버 OS:                        Linux
-- HeidiSQL 버전:                  11.3.0.6295
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

-- 테이블 ATTENDANCE.attend 구조 내보내기
CREATE TABLE IF NOT EXISTS `attend` (
  `attend_id` bigint NOT NULL AUTO_INCREMENT,
  `attend_code` varchar(255) DEFAULT NULL,
  `attend_date` varchar(255) DEFAULT NULL,
  `in_time` varchar(255) DEFAULT NULL,
  `insert_date` datetime(6) DEFAULT NULL,
  `location_id` bigint DEFAULT NULL,
  `out_time` varchar(255) DEFAULT NULL,
  `update_date` datetime(6) DEFAULT NULL,
  `work_time` bigint DEFAULT NULL,
  `member_id` bigint DEFAULT NULL,
  PRIMARY KEY (`attend_id`),
  UNIQUE KEY `UKby1o5qvcyjl4d32gydmx92wf7` (`member_id`,`attend_date`),
  CONSTRAINT `FKiaw1373ohejet2la0s226ny4m` FOREIGN KEY (`member_id`) REFERENCES `member` (`member_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- 테이블 데이터 ATTENDANCE.attend:~2 rows (대략적) 내보내기
DELETE FROM `attend`;
/*!40000 ALTER TABLE `attend` DISABLE KEYS */;
INSERT INTO `attend` (`attend_id`, `attend_code`, `attend_date`, `in_time`, `insert_date`, `location_id`, `out_time`, `update_date`, `work_time`, `member_id`) VALUES
	(1, 'AT01', '20230901', '0856', '2023-09-01 09:00:00.000000', 1, '1823', '2023-09-01 18:00:00.000000', 507, 17),
	(2, 'AT01', '20230905', '0859', '2023-09-02 09:00:00.000000', 1, '1841', '2023-09-01 18:00:00.000000', 521, 17);
/*!40000 ALTER TABLE `attend` ENABLE KEYS */;

-- 테이블 ATTENDANCE.authentication_code 구조 내보내기
CREATE TABLE IF NOT EXISTS `authentication_code` (
  `authentication_code_id` bigint NOT NULL AUTO_INCREMENT,
  `code` int NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `expiry_date` datetime(6) DEFAULT NULL,
  `is_code_entered` bit(1) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`authentication_code_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- 테이블 데이터 ATTENDANCE.authentication_code:~8 rows (대략적) 내보내기
DELETE FROM `authentication_code`;
/*!40000 ALTER TABLE `authentication_code` DISABLE KEYS */;
INSERT INTO `authentication_code` (`authentication_code_id`, `code`, `email`, `expiry_date`, `is_code_entered`, `type`) VALUES
	(1, 684676, 'zdh8456@gmail.com', '2023-10-13 09:16:08.374445', b'0', 'SIGNUP'),
	(2, 781318, 'zdh8456@gmail.com', '2023-10-13 09:19:34.499746', b'0', 'SIGNUP'),
	(3, 952575, 'zdh8456@gmail.com', '2023-10-13 09:22:09.553492', b'0', 'SIGNUP'),
	(4, 279425, 'it1713@gsitm.com', '2023-10-13 10:26:08.420325', b'1', 'PASSWORD'),
	(5, 261580, 'it1713@gsitm.com', '2023-10-13 10:30:10.120921', b'1', 'PASSWORD'),
	(6, 802439, 'it1713@gsitm.com', '2023-10-13 10:32:08.824581', b'1', 'PASSWORD'),
	(7, 863517, 'it1713@gsitm.com', '2023-10-13 10:33:06.356144', b'1', 'PASSWORD'),
	(8, 822733, 'it1713@gsitm.com', '2023-10-13 11:03:42.950832', b'1', 'SIGNUP'),
	(9, 424764, 'it1713@gsitm.com', '2023-10-13 14:27:27.968084', b'1', 'PASSWORD');
/*!40000 ALTER TABLE `authentication_code` ENABLE KEYS */;

-- 테이블 ATTENDANCE.blacklist 구조 내보내기
CREATE TABLE IF NOT EXISTS `blacklist` (
  `access_token` varchar(255) NOT NULL,
  `insert_date` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`access_token`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- 테이블 데이터 ATTENDANCE.blacklist:~2 rows (대략적) 내보내기
DELETE FROM `blacklist`;
/*!40000 ALTER TABLE `blacklist` DISABLE KEYS */;
INSERT INTO `blacklist` (`access_token`, `insert_date`) VALUES
	('eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJpdDE3MTNAZ3NpdG0uY29tIiwiYXV0aCI6IlJPTEVfTUVNQkVSIiwiaWQiOjE3LCJleHAiOjE2OTk3NjQ1MjF9.TmIdggLO53cvcBkmxTDHA7en458wM6Om2YwWaUxD6AbMvnkXx_mh6T4fR8ZsTYfa3D0TichblbstlJE5pBRqGQ', '2023-10-13 14:22:15.651019'),
	('eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJpdDE3MTNAZ3NpdG0uY29tIiwiYXV0aCI6IlJPTEVfTUVNQkVSIiwiaWQiOjE3LCJleHAiOjE2OTk3NTQzNTV9.GOZhAkYEKj-CuQmsHpLT49TF-DCXJ-PSbKtu1goFR0qfZqO-N9LeROPOSIIQQMluP3YlmItgE_gZcJU-nwcH4g', '2023-10-13 13:48:38.210256'),
	('eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJpdDE3MTNAZ3NpdG0uY29tIiwiYXV0aCI6IlJPTEVfTUVNQkVSIiwiaWQiOjE3LCJleHAiOjE2OTk3NzA4OTB9.dDz-s09IQZsnRuFIz-hGw_N29fHSuPYPKDG6TT5iOoYoPF4bkGzxgvXcPOj8cEsU6bPnxTpKfKAcYkhsRPv7TA', '2023-10-16 14:40:01.127931');
/*!40000 ALTER TABLE `blacklist` ENABLE KEYS */;

-- 테이블 ATTENDANCE.board 구조 내보내기
CREATE TABLE IF NOT EXISTS `board` (
  `board_id` bigint NOT NULL AUTO_INCREMENT,
  `content` longtext,
  `delete_yn` varchar(1) DEFAULT NULL,
  `end_date` varchar(255) DEFAULT NULL,
  `start_date` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `use_yn` varchar(1) DEFAULT NULL,
  `insert_id` varchar(256) DEFAULT NULL,
  `insert_date` datetime(6) DEFAULT NULL,
  `update_id` varchar(256) DEFAULT NULL,
  `update_date` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`board_id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- 테이블 데이터 ATTENDANCE.board:~14 rows (대략적) 내보내기
DELETE FROM `board`;
/*!40000 ALTER TABLE `board` DISABLE KEYS */;
INSERT INTO `board` (`board_id`, `content`, `delete_yn`, `end_date`, `start_date`, `title`, `type`, `use_yn`, `insert_id`, `insert_date`, `update_id`, `update_date`) VALUES
	(1, '<p><strong>안녕하세요. 0918 관리자 입니다.</strong></p><p>0918은 현재 베타 버전으로 서비스 중에 있으며 다음과 같은 서비스를 개발하고 있습니다.</p><ul><li><p>출퇴근 등록</p></li><li><p>출퇴근 기록</p></li><li><p>휴가 등록</p></li><li><p>내 정보</p></li></ul><p>추가 기능은 개발을 마치고 서비스 적용 시 즉시 공지하여 드리겠습니다.</p><p>감사합니다.</p>', 'N', '2099-09-11', '2023-09-11', '공지사항22', 'NOTICE', 'Y', NULL, NULL, NULL, NULL),
	(2, '<p><strong>안녕하세요. 0918 관리자 입니다.</strong></p><p>0918은 현재 베타 버전으로 서비스 중에 있으며 다음과 같은 서비스를 개발하고 있습니다.</p><ul><li><p>출퇴근 등록</p></li><li><p>출퇴근 기록</p></li><li><p>휴가 등록</p></li><li><p>내 정보</p></li></ul><p>추가 기능은 개발을 마치고 서비스 적용 시 즉시 공지하여 드리겠습니다.</p><p>감사합니다.</p>', 'N', '2099-09-11', '2023-09-11', '공지사항222', 'NOTICE', 'Y', NULL, NULL, NULL, NULL),
	(3, '<p><strong>안녕하세요. 0918 관리자 입니다.</strong></p><p>0918은 현재 베타 버전으로 서비스 중에 있으며 다음과 같은 서비스를 개발하고 있습니다.</p><ul><li><p>출퇴근 등록</p></li><li><p>출퇴근 기록</p></li><li><p>휴가 등록</p></li><li><p>내 정보</p></li></ul><p>추가 기능은 개발을 마치고 서비스 적용 시 즉시 공지하여 드리겠습니다.</p><p>감사합니다. test</p>', 'N', '2099-09-11', '2023-09-11', '123공지사항2222', 'NOTICE', 'Y', NULL, NULL, NULL, NULL),
	(4, '<p><strong>안녕하세요. 0918 관리자 입니다.</strong></p><p>0918은 현재 베타 버전으로 서비스 중에 있으며 다음과 같은 서비스를 개발하고 있습니다.</p><ul><li><p>출퇴근 등록</p></li><li><p>출퇴근 기록</p></li><li><p>휴가 등록</p></li><li><p>내 정보</p></li></ul><p>추가 기능은 개발을 마치고 서비스 적용 시 즉시 공지하여 드리겠습니다.</p><p>감사합니다.</p>', 'N', '2099-09-11', '2023-09-11', '공지사항22222', 'NOTICE', 'Y', NULL, NULL, NULL, NULL),
	(5, '<p><strong>안녕하세요. 0918 관리자 입니다.</strong></p><p>0918은 현재 베타 버전으로 서비스 중에 있으며 다음과 같은 서비스를 개발하고 있습니다.</p><ul><li><p>출퇴근 등록</p></li><li><p>출퇴근 기록</p></li><li><p>휴가 등록</p></li><li><p>내 정보</p></li></ul><p>추가 기능은 개발을 마치고 서비스 적용 시 즉시 공지하여 드리겠습니다.</p><p>감사합니다.</p>', 'N', '2099-09-11', '2023-09-11', '공지사항222222', 'NOTICE', 'Y', NULL, NULL, NULL, NULL),
	(6, '<p><strong>안녕하세요. 0918 관리자 입니다.</strong></p><p>0918은 현재 베타 버전으로 서비스 중에 있으며 다음과 같은 서비스를 개발하고 있습니다.</p><ul><li><p>출퇴근 등록</p></li><li><p>출퇴근 기록</p></li><li><p>휴가 등록</p></li><li><p>내 정보</p></li></ul><p>추가 기능은 개발을 마치고 서비스 적용 시 즉시 공지하여 드리겠습니다.</p><p>감사합니다.</p>', 'N', '2099-09-11', '2023-09-11', '123공지사항2222222', 'NOTICE', 'Y', NULL, NULL, NULL, NULL),
	(7, '<p><strong>안녕하세요. 0918 관리자 입니다.</strong></p><p>0918은 현재 베타 버전으로 서비스 중에 있으며 다음과 같은 서비스를 개발하고 있습니다.</p><ul><li><p>출퇴근 등록</p></li><li><p>출퇴근 기록</p></li><li><p>휴가 등록</p></li><li><p>내 정보</p></li></ul><p>추가 기능은 개발을 마치고 서비스 적용 시 즉시 공지하여 드리겠습니다.</p><p>감사합니다.</p>', 'N', '2099-09-11', '2023-09-11', '공지사항22222222', 'NOTICE', 'Y', NULL, NULL, NULL, NULL),
	(8, '<p><strong>안녕하세요. 0918 관리자 입니다.</strong></p><p>0918은 현재 베타 버전으로 서비스 중에 있으며 다음과 같은 서비스를 개발하고 있습니다.</p><ul><li><p>출퇴근 등록</p></li><li><p>출퇴근 기록</p></li><li><p>휴가 등록</p></li><li><p>내 정보</p></li></ul><p>추가 기능은 개발을 마치고 서비스 적용 시 즉시 공지하여 드리겠습니다.</p><p>감사합니다.</p>', 'N', '2099-09-11', '2023-09-11', '공지사항222222222', 'NOTICE', 'Y', NULL, NULL, NULL, NULL),
	(9, '<p><strong>안녕하세요. 0918 관리자 입니다.</strong></p><p>0918은 현재 베타 버전으로 서비스 중에 있으며 다음과 같은 서비스를 개발하고 있습니다.</p><ul><li><p>출퇴근 등록</p></li><li><p>출퇴근 기록</p></li><li><p>휴가 등록</p></li><li><p>내 정보</p></li></ul><p>추가 기능은 개발을 마치고 서비스 적용 시 즉시 공지하여 드리겠습니다.</p><p>감사합니다.</p>', 'N', '2099-09-11', '2023-09-11', '공지사항2222222222', 'NOTICE', 'Y', NULL, NULL, NULL, NULL),
	(10, '<p><strong>안녕하세요. 0918 관리자 입니다.</strong></p><p>0918은 현재 베타 버전으로 서비스 중에 있으며 다음과 같은 서비스를 개발하고 있습니다.</p><ul><li><p>출퇴근 등록</p></li><li><p>출퇴근 기록</p></li><li><p>휴가 등록</p></li><li><p>내 정보</p></li></ul><p>추가 기능은 개발을 마치고 서비스 적용 시 즉시 공지하여 드리겠습니다.</p><p>감사합니다.</p>', 'N', '2099-09-11', '2023-09-11', '공지사항22222222222', 'NOTICE', 'Y', NULL, NULL, NULL, NULL),
	(11, '<p><strong>안녕하세요. 0918 관리자 입니다.</strong></p><p>0918은 현재 베타 버전으로 서비스 중에 있으며 다음과 같은 서비스를 개발하고 있습니다.</p><ul><li><p>출퇴근 등록</p></li><li><p>출퇴근 기록</p></li><li><p>휴가 등록</p></li><li><p>내 정보</p></li></ul><p>추가 기능은 개발을 마치고 서비스 적용 시 즉시 공지하여 드리겠습니다.</p><p>감사합니다.</p>', 'N', '2099-09-11', '2023-09-11', '공지사항222222222222', 'NOTICE', 'Y', NULL, NULL, NULL, NULL),
	(12, '<p><strong>안녕하세요. 0918 관리자 입니다.</strong></p><p>0918은 현재 베타 버전으로 서비스 중에 있으며 다음과 같은 서비스를 개발하고 있습니다.</p><ul><li><p>출퇴근 등록</p></li><li><p>출퇴근 기록</p></li><li><p>휴가 등록</p></li><li><p>내 정보</p></li></ul><p>추가 기능은 개발을 마치고 서비스 적용 시 즉시 공지하여 드리겠습니다.</p><p>감사합니다.</p>', 'N', '2099-09-11', '2023-09-11', '공지사항2222222222222', 'NOTICE', 'Y', NULL, NULL, NULL, NULL),
	(13, '<p><strong>안녕하세요. 0918 관리자 입니다.</strong></p><p>0918은 현재 베타 버전으로 서비스 중에 있으며 다음과 같은 서비스를 개발하고 있습니다.</p><ul><li><p>출퇴근 등록</p></li><li><p>출퇴근 기록</p></li><li><p>휴가 등록</p></li><li><p>내 정보</p></li></ul><p>추가 기능은 개발을 마치고 서비스 적용 시 즉시 공지하여 드리겠습니다.</p><p>감사합니다.</p>', 'Y', '2099-09-11', '2023-09-11', '공지사항22222222222222', 'NOTICE', 'Y', NULL, NULL, NULL, NULL),
	(15, '<p>1234</p>', 'N', '2023-10-23', '2023-10-16', '공지등록 테스트', 'NOTICE', 'Y', NULL, NULL, NULL, NULL),
	(16, '<h2><strong>개인정보처리방침</strong></h2><p>지에스아이티엠 (이하 "회사")은 개인정보를 소중하게 생각하고 개인정보를 보호하기 위하여 최선을 다하고 있습니다. "회사"는 본 개인정보처리방침을 통하여 이용자가 제공하는 개인정보가 어떠한 용도와 방식으로 이용되고 있으며 개인정보보호를 위해 어떠한 조치가 취해지고 있는지 알려드리고자 합니다. 본 개인정보처리방침은 정부의 법률 및 지침 변경이나 "회사"의 내부 방침 변경 등으로 인하여 수시로 변경될 수 있으며, 변경될 경우 변경된 개인정보처리방침을 "회사"가 제공하는 서비스 페이지에 공지하도록 하겠습니다.</p><p><strong>"회사"의 개인정보처리방침은 다음과 같은 내용을 포함하고 있습니다.</strong></p><p>1. 개인정보의 수집 및 이용 목적, 항목 및 수집방법</p><p>2. 개인정보의 제공 및 처리위탁</p><p>3. 개인정보 수집·이용의 거부 시 불이익</p><p>4. 개인정보 보유 및 이용기간</p><p>5. 개인정보의 파기절차 및 방법</p><p>6. 회원의 권리와 행사 방법</p><p>7. 개인정보 자동 수집 장치의 설치/운영 및 거부에 관한 사항</p><p>8. 개인정보 보호를 위한 기술적/관리적 대책</p><p>9. 개인정보 관리 책임자의 성명, 연락처, 부서</p><p>10. 고지의 의무</p><p>&nbsp;<strong>1. 개인정보 수집의 이용 목적, 항목 및 수집방법</strong></p><p>1. “개인정보”란 생존하는 개인에 관한 정보로서 당해 개인을 식별할 수 있는 정보(당해 정보만으로는 특정 개인을 식별할 수 없더라도 다른 정보와 용이하게 결합하여 식별할 수 있는 것을 포함합니다)를 말합니다.</p><p>2. "회사"는 수집된 개인정보를 다음의 목적을 위해 활용합니다.</p><p>a. 회원가입 및 의사소통에 관한 사항: 회원 본인확인, 부정 가입방지, 가입 의사확인, 연령확인, 고충상담 및 처리, 공지사항 안내, 약관 위반 회원에 대한 이용제한 조치, 서비스의 원활한 운영에 지장을 미치는 행위 및 서비스 부정이용 행위 제재, 회원 가입 거절 사유 유무의 확인, 분쟁 조정을 위한 기록보존, 불만처리 등 민원처리 등</p><p>b. 서비스 개선 및 이용에 관한 통계 조사 등, 신규 서비스 개발, 통계학적 특성에 따른 서비스 제공 및 광고 게재, 서비스의 유효성 확인, 접속빈도 파악, 회원의 서비스 이용에 대한 통계 분석 등 서비스 분석 및 서비스 이용환경 개선</p><p>c. 회원으로부터 이에 대한 별도의 동의를 얻은 경우에 한하여, 이벤트 정보 및 참여기회 제공, 광고성 정보 제공 및 참여기회 제공</p><p>3. "회사"는 위에 명시한 목적을 위해 다음과 같은 개인정보를 수집하고 있습니다.</p><p><strong>a. 개인회원 통합회원 가입시</strong></p><p>●&nbsp;&nbsp; 필수: 이메일, 비밀번호, 본인확인값(DI, CI), 이름, 휴대폰번호, 소속</p><p>●&nbsp;&nbsp; 서비스 이용 과정 중 자동 생성 수집 정보</p><p>●&nbsp;&nbsp; IP Address, MAC 주소, 쿠키, 세션, 방문일시, 서비스 이용기록, 불량(부정) 이용기록, 기기정보(OS, 단말기 모델, 이동통신사 정보, 하드웨어 ID, 서비스 이용에 대한 기본 통계), 어플리케이션 설치 및 사용이력</p><p>●&nbsp;&nbsp; IOS 운영체제 휴대폰을 사용하는 회원의 경우 사용자 식별을 위하여 UUID에 접근할 수 있음.</p><p>●&nbsp;&nbsp; 회원의 휴대전화 단말기의 카메라, 사진첩, 마이크 기능(IOS) 또는 저장공간(사진/미디어/파일)(Android)에 접근할 수 있음.</p><p>4. "회사"는 이용자의 개인정보의 수집할 경우 반드시 이용자의 동의를 얻어 수집하며, 이용자의 기본적 인권을 침해할 우려가 있는 인종, 출신지, 본적지, 사상, 정치적 성향, 범죄기록, 건강상태 등의 정보는 이용자의 동의 또는 법령의 규정에 의한 경우 이외에는 수집하지 않습니다.</p><p>5. "회사"는 회원 가입을 만 14세 이상인 경우에 가능하도록 하며 개인정보의 수집·이용에 법정대리인의 동의가 필요한 만 14세 미만 아동의 개인정보는 수집하지 않습니다.</p><h3><strong>2. 개인정보의 제공 및 처리위탁</strong></h3><p><strong>1. 개인정보의 제공</strong></p><p>a. "회사"는 이용자의 개인정보를 “1. 개인정보의 수집 및 이용 목적, 항목 및 수집 방법"에서 고지한 범위 내에서 사용하며, 원칙적으로 이용자의 개인정보를 제공하지 않습니다. 다만 아래의 경우에는 예외로 합니다.</p><p>i. 이용자가 사전에 동의한 경우</p><p>ii. 법령의 규정에 의거하거나, 수사 목적으로 법령에 정해진 절차와 방법에 따라 수사기관의 요구가 있는 경우</p><p><strong>2. 개인정보 처리의 위탁</strong></p><p>a. "회사"는 원활한 업무 처리를 위해서 아래와 같이 개인정보를 위탁하고 있으며, 관계 법령에 따른 위탁계약 시 개인정보가 안전하게 관리될 수 있도록 필요한 사항을 규정하고 있습니다. 회사의 개인정보 위탁처리 기관 및 위탁업무 내용은 아래와 같습니다. 위탁기간은 회원의 탈퇴시 또는 위탁계약 종료시까지입니다.</p><p>i. 국내 위탁 업체 <strong>(대현아 예시)</strong></p><table><thead><tr><th><p><strong>제공받은 자</strong></p></th><th><p><strong>제공하는 목적</strong></p></th></tr></thead><tbody><tr><td><p>NHN 클라우드㈜</p></td><td><p>카카오톡 알림 서비스 및 문자메시지 발송</p></td></tr><tr><td><p>㈜카카오</p></td><td><p>카카오 채널을 통한 마케팅 목적 알림 메세지 발송</p></td></tr></tbody></table><p>&nbsp;</p><p>ii. 국외 위탁업체 <strong>(대현아 예시)</strong></p><table><thead><tr><th><p><strong>제공업체</strong></p></th><th><p><strong>업체 연락처</strong></p></th><th><p><strong>이전 목적</strong></p></th><th><p><strong>이전되는 개인정보 항목</strong></p></th><th><p><strong>이전 국가</strong></p></th><th><p><strong>이용 기간</strong></p></th><th><p><strong>이전 일시 및 방법</strong></p></th></tr></thead><tbody><tr><td><p>Amazon Web Servises Inc.</p><p>(AWS Server)</p></td><td><p>aws-korea-privacy@amazon.com</p></td><td><p>데이터 저장을 위한 클라우드 서버 이용</p></td><td><p>서비스 이용 기록 또는 수집된 개인정보</p></td><td><p>미국, 일본 등</p></td><td><p>이용자의 서비스 가입기간 동안 보유</p></td><td><p>서비스 이용 시점에 네트워크를 통한 전송</p></td></tr></tbody></table><p>&nbsp;</p><p>&nbsp;</p><p><strong>3. 개인정보 수집·이용의 거부 시 불이익</strong></p><p>회원은 개인정보 수집·이용에 관한 동의를 거부할 권리가 있습니다. 다만, 회원이 개인정보의 수집·이용에 관한 동의를 거부할 경우 불가피하게 아래와 같은 불이익이 발생합니다.</p><p>a. 회원이 회원 가입 신청 시 회사가 수집·이용(마켓팅, 프로모션, 광고 목적 수집·이용 제외)하고자 하는 개인정보의 수집·이용을 거부하는 경우에는 회원의 회원 가입 또는 서비스 이용이 불가능합니다.</p><p>b. 회원의 서비스 이용 과정에서 회사가 수집·이용 또는 접근하는 개인정보 또는 휴대전화기능의 수집·이용 또는 접근을 거부하는 경우 해당 회원에 대한 서비스의 제공 및 기술 지원이 제한될 수 있습니다.</p><p>&nbsp;</p><p><strong>4. 개인정보 보유 및 이용기간</strong></p><p>1. "회사"는 회원 가입 시 제공한 정보를 회원 가입시점부터 탈퇴 신청이 접수된 날까지 보관할 수 있습니다. 단, 개인정보 도용 등으로 인한 원치 않는 회원탈퇴 등에 대비하기 위해 회원탈퇴 요청 후 [7]일간 개인정보를 보존하고, 서비스의 부정한 이용으로 인한 분쟁을 방지하기 위한 내부 방침에 따라 서비스의 부정이용기록, 즉 이용약관 및 “회사”의 정책에 위배되는 행위를 한 회원의 개인정보는 1년간 보존합니다.</p><p>2. 단, 관계법령의 규정에 의하여 보존할 필요가 있는 경우 "회사"는 아래와 같이 관계법령에서 정한 일정한 기간 동안 회원정보를 보관합니다</p><p>a. 개별적으로 회원의 동의를 받은 경우에는 약속한 보유기간</p><p>b. 보존근거: 전자상거래 등에서의 소비자보호에 관한 법률, 통신비밀보호법 등 관련법령</p><p>c. 보존기간:</p><p>i. 계약 또는 청약철회 등에 관한 기록(전자상거래법): 5년</p><p>ii. 대금결제 및 재화 등의 서비스 등의 공급에 관한 기록(전자상거래법): 5년</p><p>iii. 소비자의 불만 또는 분쟁처리에 관한 기록(전자상거래법): 3년(다만, 민사, 형사, 행정상의 절차가 진행 중에 있는 경우에는 그 절차가 종료될 때까지)</p><p>iv. 표시/광고에 관한 기록(전자상거래법): 6개월</p><p>v. 세법이 규정하는 모든 거래에 관한 장부 및 증빙서류(국세기본법): 5년</p><p>vi. 전자금융 거래에 관한 기록(전자금융거래법): 5년</p><p>iv. 접속에 관한 기록(통신비밀보호법): 3개월</p><p>&nbsp;</p><p><strong>5. 개인정보의 파기절차 및 방법</strong></p><p>1. 회사는 개인정보 보유 및 이용 기간이 경과하면 다음과 같은 방법으로 지체 없이 파기합니다.</p><p>a. 전자적 파일 형태인 경우: 복원이 불가능한 방법으로 영구 삭제</p><p>b. 인쇄물, 서면, 그 밖의 기록매체인 경우: 파쇄 또는 소각</p><p>2. 회사는 위 기준과 별도로 1년간 서비스를 이용하지 않은 회원의 개인정보는 휴면 계정으로 전환하여 별도로 분리하여 보관하거나 삭제할 수 있습니다.</p><p>&nbsp;</p><p><strong>6. 회원의 권리와 행사 방법</strong></p><p>1. 회원은 언제든지 다음의 사항에 관하여 개인정보 열람, 수정 및 회원 탈퇴를 요구할 수 있습니다. 다만 회원이 회원가입 및 서비스 제공에 필요한 개인정보 등에 대한 동의를 철회하는 경우 회원탈퇴 및 서비스 해지가 불가피하게 되거나 서비스를 제공받지 못하게 될 수 있습니다.</p><p>a. "회사"가 보유하고 있는 회원의 개인정보</p><p>b. "회사"가 이용자의 개인정보를 이용하거나 제3자에게 제공한 내역</p><p>c. "회사"에게 개인정보수집ㆍ이용ㆍ제공 등의 동의를 한 내역</p><p>2. 회원은 "회사" 서비스에서 직접 자신의 정보를 열람, 정정을 할 수 있으며, 별도로 개인정보보호책임자에게 서면, 전화, 이메일 등을 통하여 개인정보의 열람, 정정을 요청할 수 있습니다.</p><p>3. 회원은 언제든지 자신의 개인정보처리의 정지를 요구할 수 있습니다.</p><p>4. 회원은 언제든지 회원가입 시 개인정보의 수집, 이용, 제공 등에 대해 동의하신 의사표시를 철회(회원탈퇴)할 수 있습니다.</p><p>5. 회원이 "회사" 서비스에서 본인 확인 절차를 거친 후 직접 동의철회(회원탈퇴)를 하거나, 별도로 개인정보보호책임자에게 서면, 전화 또는 이메일 등을 통하여 연락하면 지체 없이 이용자의 개인정보를 파기하는 등 필요한 조치를 취합니다. 단, 동의철회(회원탈퇴)가 있더라도 관계법령에 따라 최소한의 정보가 보관됩니다.</p><p>&nbsp;</p><p><strong>7. 개인정보 자동 수집 장치의 설치/운영 및 거부에 관한 사항</strong></p><p>1. "회사"는 회원의 정보를 수시로 저장하고 불러오는 "쿠키"(cookie)와 세션(session)를 사용합니다. "쿠키"는 웹사이트 이용 시 서버가 사용자의 웹브라우저에 보내는 작은 데이터 꾸러미로 회원의 컴퓨터의 하드디스크에 저장됩니다. 세션(session)은 서비스 운영에 이용되는 서버가 회원의 접속시간 동안에 회원의 정보를 서버에 저장하는 것을 의미합니다. "회사"는 다음과 같은 목적을 위해 "쿠키"와 “세션”를 사용합니다.</p><p>2. “쿠키”와 “세션”은 이용자가 설정한 환경을 유지하도록 함으로써 편리한 사용을 도우며, 이용자의 방문 기록, 이용 형태, 관심 분야를 알게 해줌으로써 이를 통한 최적화된 맞춤 서비스를 제공하고 서비스 개선의 척도로 활용됩니다.</p><p>3. 이용자는 "쿠키" 설치에 대한 선택권을 가지고 있으며 웹브라우저에서 옵션을 설정함으로써 모든 "쿠키"를 허용하거나, "쿠키"가 저장될 때마다 확인을 거치거나, 아니면 모든 "쿠키"의 저장을 거부할 수 있습니다. 다만, 쿠키 설치를 거부할 경우 로그인이 필요한 일부 서비스를 이용하는 것에 어려움이 있을 있습니다. 쿠키 설치 허용 여부를 지정하는 방법은 다음과 같습니다.</p><p>i. Chrome 웹 브라우저 &gt; 우측 상단 메뉴 [설정] &gt; [사이트 설정] &gt; [쿠키] 설정</p><p>ii. Safari 웹 브라우저 &gt; [설정] &gt; [Safari] &gt; [쿠키 차단] &gt; 설정</p><p>4. 이용자는 “세션” 설치에 대한 선택권을 가지고 있지 않으며, 로그인이 필요한 서비스 이용 시 서버에 자동으로 “세션”이 생성됩니다.</p><p>5. 회사는 회원이 스마트폰, 태블릿 PC의 [연락처 기능, 카메라 및 사진첩 기능, 위치 서비스 기능]에 회사가 접근하는 것을 원하지 않을 경우 회원 스스로 접근을 차단할 수 있는 기술적 수단을 제공해 드립니다(즉, 회원이 휴대전화에서 [**] 앱(App)의 위 각 기능에 대한 접근을 차단 또는 비활성화하면 됩니다.)</p><p>&nbsp;</p><p><strong>8. 개인정보 보호를 위한 기술적/관리적 대책</strong></p><p>1. 회원의 개인정보는 비밀번호에 의해 철저히 보호되고 있으며 본인이 제공한 이메일주소에 매칭되는 비밀번호는 본인만이 알고 있습니다. 따라서 개인정보의 확인 및 변경도 비밀번호를 알고 있는 본인에 의해서만 가능합니다.</p><p>2. 회원은 본인의 비밀번호를 누구에게도 알려주면 안됩니다. 이를 위해 "회사"에서는 기본적으로 PC에서의 사용을 마치신 후 온라인상에서 로그아웃(LOG-OUT)하고 웹 브라우저를 종료하도록 권장합니다.</p><p>3. "회사"의 개인정보 보호를 위한 기술적/관리적 대책 및 물리적 조치는 다음과 같습니다.</p><p>a. 수집한 개인정보 중 비밀번호 등 본인임을 인증하는 정보에 대한 암호화 장치</p><p>b. 컴퓨터 바이러스에 의한 개인정보의 침해를 막기 위한 백신소프트웨어 설치, 갱신, 점검 조치</p><p>c. 개인정보 시스템에 대한 접근권한 설정, 관리 및 침입차단 시스템 등을 이용한 접근 통제장치</p><p>d. 개인정보 취급자에 대한 지정과 권한의 설정 및 교육, 개인정보의 안전한 관리</p><p>&nbsp;</p><p><strong>9. 개인정보보호책임자의 성명, 연락처, 부서</strong></p><p>회사의 서비스를 이용하시면서 발생한 모든 개인정보보호 관련 민원, 불만처리 등에 관한 사항을 개인정보 보호책임자 및 고객센터로 문의하실 수 있고, 회사는 이용자의 문의에 신속하고 성실하게 답변하겠습니다.</p><p>●&nbsp;&nbsp; 이 름: 장대현</p><p>●&nbsp;&nbsp; 소 속: 모바일팀</p><p>●&nbsp;&nbsp; 연락처: 010-1111-1111</p><p>●&nbsp;&nbsp; E-mail: jhjghgjjggh</p><p>기타 개인정보침해에 관한 상담이 필요한 경우에는 한국정보보호진흥원, 대검찰청 인터넷범죄수사센터, 경찰청 사이버테러대응센터 등으로 문의하실 수 있습니다.</p><p>●&nbsp;&nbsp; 한국정보보호진흥원 (1336)</p><p>●&nbsp;&nbsp; 대검찰청 인터넷범죄수사센터 (02-3480-3600)</p><p>●&nbsp;&nbsp; 경찰청 사이버테러대응센터 (02-392-0330)</p><p>&nbsp;</p><p><strong>10. 고지의 의무</strong></p><p>회사는 개인정보처리방침이 변경되는 경우에는 “회사”의 사이트 “공지사항”을 통하여 변경 및 시행의 시기, 변경 내용을 공지합니다. 변경된 개인정보처리방침은 게시된 날로부터 7일 후부터 효력이 발생합니다.</p><p>&nbsp;</p><p>●&nbsp;&nbsp; 시행일자 : 2023년 8월 31일</p><p>&nbsp;</p><p>&nbsp;</p>', 'N', '2099-10-23', '2023-10-15', 'test', 'PRIVACY', 'Y', NULL, NULL, NULL, NULL),
	(17, '<p>이용약관</p><p>&nbsp;</p><p>제1장 총칙</p><p>&nbsp;</p><p><strong>제1조 목적및 용어 정의</strong></p><p>본 약관은 ㈜지에스아이티엠에서 제공하는 0918을 이용함에 있어 회사와 회원의 권리, 의무 및 기타 필요한 사항을 규정함을 목적으로 합니다.</p><p>본 약관에서 사용하는 용어의 정의는 다음과 같습니다.</p><p>&nbsp;</p><p>회사 : 0918을 운영하는 ㈜지에스아이티엠을 말합니다.</p><p>서비스 : 0918에서 제공하는 서비스를 말합니다.</p><p>회원 : 서비스 이용을 위하여 본 약관에 동의함으로써 회사와 이용계약을 체결한 자를 말합니다.</p><p>회원 아이디(ID) : 회원의 식별과 회원의 서비스 이용을 위하여 회사에서 부여받은 메일을&nbsp; 말합니다.</p><p>비밀번호 : 회원 아이디(ID)를 사용하는 자가 회사와 이용계약을 체결한 회원 본인임을 확인하고, 회원의 개인정보를 보호하기 위하여 회원이 정한 문자와 숫자, 특수문자의 조합을 말합니다.</p><p>가입해지 : 회사 또는 회원이 이용계약을 해지하는 것을 말합니다.</p><p>휴면 아이디(ID) : 연속하여 12개월 이상 서비스를 이용하기 위한 log-in 기록이 없는 아이디(ID)를 말합니다.</p><p>&nbsp;</p><p><strong>제2조 약관의 효력 및 변경</strong></p><p>①본 약관의 내용은 0918앱의 화면에 게시하여 공지하고, 본 약관에 동의한 회원에게 그 효력이 발생합니다.</p><p>②회사는 필요한 경우 관련법령을 위배하지 않는 범위 내에서 본 약관을 변경할 수 있습니다. 본 약관이 변경되는 경우 회사는 변경사항을 시행일자 15일 전부터 회원에 공지사항에서 공지 또는 통지하는 것을 원칙으로 하며, 피치 못하게 회원에 불리한 내용으로 변경할 경우에는 그 시행일자 30일 전부터 사내메일 주소로 발송 또는 회원이 등록한 휴대폰번호로 문자메시지 발송하는 방법 등으로 개별적으로 알려 드리겠습니다.</p><p>③회사가 전항에 따라 공지 또는 통지를 하면서 공지 또는 통지일로부터 개정약관 시행일 7일 후까지 거부의사를 표시하지 아니하면 승인한 것으로 본다는 뜻을 명확하게 공지 하였음에도 여러분의 의사표시가 없는 경우에는 변경된 약관을 승인한 것으로 봅니다. 회원이 퇴사를 함과 동시에 이용계약을 해지할 수 있습니다.</p><p>&nbsp;</p><p><strong>제3조 약관외 준칙</strong></p><p>본 약관에 규정되지 않은 사항에 대해서는 관련법령 또는 회사가 정한 개별 서비스의 이용약관, 운영정책 및 규칙 등(이하 ‘세부지침’)의 규정에 따릅니다.</p><p>&nbsp;</p><p>&nbsp;</p><p>제2장 이용계약의 체결</p><p>&nbsp;</p><p><strong>제4조 이용신청의 방법</strong></p><p>① 0918계정 이용 신청은 0918앱 회원가입 화면에서 회원이 사내메일 정보를 입력하는 방식으로 이루어집니다.</p><p>② 0918 이용계약은 회원이 본 약관의 내용에 동의한 후 본 조 제1항에서 정한 이용신청을 하면 회사가 입력된 일정 정보를 인증한 후 가입을 승낙함으로써 체결됩니다.</p><p>&nbsp;</p><p>&nbsp;</p><p><strong>제5조 이용신청의 승낙과 제한</strong></p><p>회사가 회원의 정당한 이용신청에 대하여 승낙함으로써 이용계약이 성립합니다.</p><p>회사는 다음 각 호의 어느 하나에 해당하는 경우 이용신청에 대한 승낙을 거절하거나 그 사유가 해소될 때까지 승낙을 유보할 수 있습니다.</p><p>(1) 회사의 서비스 관련설비에 여유가 없는 경우</p><p>(2) 회사의 기술상 지장이 있는 경우</p><p>(3) 기타 회사의 사정상 필요하다고 인정되는 경우</p><p>회사는 다음 각 호의 어느 하나에 해당하는 경우 회원으로 가입하고자 하는 자의 이용신청을 승낙하지 아니할 수 있습니다.</p><p>(1) 본인이 아니거나 타인의 명의를 이용하여 신청한 경우</p><p>(2) 14세 미만의 아동</p><p>(3) 이용신청서 내용을 허위로 기재하여 신청한 경우</p><p>(4) 사회의 안녕과 질서 혹은 미풍양속을 저해할 목적으로 신청한 경우</p><p>(5) 부정한 용도로 서비스를 이용하고자 하는 경우</p><p>(6) 영리를 추구할 목적으로 서비스를 이용하고자 하는 경우</p><p>(7) 기타 회사가 정한 이용신청 요건을 미비한 경우</p><p>회사는 이용승낙의 목적을 위하여 본인확인기관을 통하여 회원이 본인임을 확인하는 절차를 진행할 수 있습니다.</p><p>&nbsp;</p><p><strong>제6조 개인정보처리방침</strong></p><p>회사는 「정보통신망 이용촉진 및 정보보호 등에 관한 법률」 및 「개인정보 보호법」 등 관련법령을 준수하고 회원의 개인정보를 보호하기 위하여 노력하고 있습니다. 회원의 개인정보 처리에 관한 사항은 세이클럽 개인정보처리방침을 따릅니다.</p><p>&nbsp;</p><p><strong>제7조 개인정보의 보호</strong></p><p>회사는 회원이 안전하게 서비스를 이용할 수 있도록 회원의 개인정보 보호를 위한 보안시스템을 구축하며, 관련법령 및 개인정보처리방침에 따라 회원의 개인정보를 보호합니다.</p><p>회원은 서비스 내 [설정] -&gt; [마이페이지]메뉴를 통하여 자신의 개인정보를 열람할 수 있으며, 해당 페이지에서 언제든지 본인의 개인정보를 열람하고 수정할 수 있습니다. 다만, 회원의 본인확인 절차를 통하여 수집한 실명, 생년월일 등은 수정할 수 없습니다.</p><p>&nbsp;</p><p>&nbsp;</p><p>제3장 계약 당사자의 의무</p><p><strong>&nbsp;</strong></p><p><strong>제8조 회사의 의무</strong></p><p>회사는 관련법령과 본 약관이 금지하는 행위, 공서양속에 반하는 행위를 하지 않으며, 본 약관이 정하는 바에 따라 지속적이고 안정적으로 서비스를 제공하기 위하여 최선을 다합니다.</p><p>회사는 이용계약의 체결, 본 약관의 변경 및 해지 등 회원과의 계약관계에 있어 회원에게 편의를 제공하도록 노력합니다.</p><p>회사는 회원이 서비스를 이용하는데 필요한 최소한의 기술사양에 관한 정보를 각 콘텐츠 별 서비스 페이지에서 제공합니다.</p><p>회사는 정보통신망 이용촉진 및 정보보호 등에 관한 법률, 통신비밀보호법, 전기통신사업법 등 관계법령을 준수합니다.</p><p>&nbsp;</p><p><strong>제9조 회원의 아이디(ID)및 비밀번호에 대한 관리의무</strong></p><p>회사는 회원 아이디(ID), 비밀번호를 회원 본인이 사용 및 관리하는 것으로 간주합니다.</p><p>회원은 회원 아이디(ID), 비밀번호를 타인이 사용 및 관리하도록 허락할 수 없습니다.</p><p>회사가 관련법령 및 개인정보처리방침에 의하여 그 책임을 지는 경우를 제외하고, 회원 아이디(ID)와 비밀번호의 관리책임은 회원에게 있습니다.</p><p>회사는 아이디(ID)의 도용, 회원 및 제3자에 의한 아이디(ID)의 사용에 의한 결과에 대하여 책임지지 않습니다.</p><p>본조 제4항의 아이디(ID)의 도용 또는 제3자가 아이디(ID)를 사용함을 인지한 경우 회원은 이를 지체 없이 회사에 통보하고 회사의 안내에 따라야 합니다. 회원이 본조의 아이디(ID)의 도용 또는 본조 제2항의 통지를 누락하여 발생한 불이익에 대하여 회사는 책임지지 않습니다.</p><p>&nbsp;</p><p><strong>제10조 회원의 의무</strong></p><p>회원은 본 약관에 규정하는 사항과 서비스 이용안내 또는 공지사항 등 회사가 공지 또는 통지하는 사항을 준수하여야 하며, 기타 회사의 업무에 방해되는 행위를 하여서는 안됩니다.</p><p>회원은 회사의 사전승낙이 없이 영리를 목적으로 서비스를 이용할 수 없으며, 회원간 또는 회원과 제3자간에 서비스를 매개로 한 물품거래 및 서비스의 이용과 관련하여 기대하는 이익 등에 관하여 발생하는 손해에 대하여 회사는 책임을 지지 않습니다. 이와 같은 영업활동으로 회사가 손해를 입은 경우 회원은 회사에 대하여 손해배상의 의무를 집니다.</p><p>회원은 회사의 명시적인 동의가 없는 한 서비스의 이용권한, 기타 이용계약상 지위를 타인에게 양도, 증여할 수 없으며, 이를 담보로 제공할 수 없습니다.</p><p>회원은 서비스 이용과 관련하여 제22조 제1항의 각 호에 해당하는 행위를 하여서는 안됩니다.</p><p>회원은 제22조 제1항 제(8)호의 행위를 하는 자를 발견한 경우 회사에 즉시 알려야 합니다.</p><p>&nbsp;</p><p>&nbsp;</p><p>제4장 서비스 이용</p><p><strong>&nbsp;</strong></p><p><strong>제11조 &nbsp;서비스의 변경 및 종료</strong></p><p>&nbsp;</p><p>①회사는 0918을 365일, 24시간 쉬지 않고 제공하기 위하여 최선의 노력을 다합니다. 다만, 아래 각 호의 경우0918의 서비스가 전부 또는 일부를 제한하거나 중지할 수 있습니다.</p><p>&nbsp;</p><p>1.0918계정 서비스용 설비의 유지·보수 등을 위한 정기 또는 임시 점검의 경우</p><p>2.정전, 제반 설비의 장애 또는 이용량의 폭주 등으로 정상적인 이용에 지장이 있는 경우</p><p>3.관계사와의 계약 종료, 정부의 명령/규제, 서비스/회원 정책 변경 등 회사의 제반 사정으로 서비스를 유지할 수 없는 경우</p><p>4.기타 천재지변, 국가비상사태 등 불가항력적 사유가 있는 경우</p><p>&nbsp;</p><p>②전항에 의한 0918서비스 중단의 경우에는 미리 제14조에서 정한 방법으로 여러분에게 통지 내지 공지하겠습니다. 이 때 원만한 서비스 및 정책 변경 등을 위하여 서비스 이용 시 재로그인 또는 추가적인 동의 절차 등이 필요할 수 있습니다. 다만, 회사로서도 예측할 수 없거나 통제할 수 없는 사유(회사의 과실이 없는 디스크 내지 서버 장애, 시스템 다운 등)로 서비스가 중단된 경우에는 사전 통지 내지 공지를 할 수 없습니다. 이러한 경우에도 회사가 상황을 파악하는 즉시 최대한 빠른 시일 내에 서비스를 복구하도록 노력하되, 2시간 이상 복구가 지연될 시 공지사항에 게시하여 알려 드리겠습니다.</p><p>&nbsp;</p><p>&nbsp;</p><p><strong>제12조 데이터 및 기타자료의 수집 및 사용</strong></p><p>회사는 서비스와 관련하여 서비스의 품질개선 및 안전한 서비스 제공을 위한 목적으로 회원의 단말기 정보 등을 수집하여 사용할 수 있습니다.</p><p>회사는 전항의 자료에 대하여 소프트웨어를 개선하거나 회원의 사용환경에 적합한 서비스 또는 기술을 제공하기 위한 목적으로만 사용하며 그 외의 다른 목적으로 사용하지 않습니다.</p><p>회사는 안정적이고 보안이 강화된 고품질의 서비스를 제공하기 위한 다자간 데이터 송수신기술(멀티캐스팅), 디지털 저작권 관리기술(Digital Right Management), 그리드 컴퓨팅(Grid Computing)과 같은 적절한 기술을 적용할 수 있으며 이를 위해 회원의 단말기에 관련 소프트웨어를 설치할 수 있습니다.</p><p>&nbsp;</p><p><strong>제 13조 인증서비스</strong></p><p>①인증회원은 회사가 정한 방법에 따라 인증서비스를 이용하여야 합니다. 또한 인증회원은 자신의 전자서명생성정보와 인증서 및 이와 관련된 모든 정보를 안전하게 관리하고 인증서비스 이용 기간 중 회사에 제공한 정보 및 인증서에 포함된 정보가 정확하고 완전하게 유지되도록 하여야 합니다. 인증회원은 자신의 전자서명생성정보와 인증서 및 이에 관련된 정보를 타인에게 양도, 증여, 판매, 사용 허락할 수 없으며, 분실, 훼손, 도난 또는 유출되거나 그러할 위험이 있다고 인지한 경우 즉시 그 사실을 회사에 통지하여야 합니다.</p><p>&nbsp;</p><p>4.회사로부터 이용 정지를 당하거나, 법령 또는 본 약관을 위반하는 등의 이유로 서비스 이용 계약이 해지된 회원이 재이용신청을 하는 경우</p><p>&nbsp;</p><p>5.기타 회원의 귀책사유로 발급이 곤란한 경우 또는 회사가 정한 이용신청 요건이 충족되지 않은 경우</p><p>⑤인증회원은 인증서비스를 자유롭게 이용할 수 있으나, 아래 각 호의 행위는 하여서는 안 됩니다.</p><p>&nbsp;</p><p>1.회사가 정하지 않은 비정상적인 방법으로 시스템에 접근하거나 인증서비스를 이용하는 행위</p><p>2.부정한 방법으로 인증서를 발급받거나 행사하는 등 인증서비스를 불법적 또는 부당한 용도로 사용하는 행위</p><p>3.인증회원이 인증서의 비밀번호를 연속하여 제한 횟수 이상 잘못 입력한 경우</p><p>4.인증회원의 퇴사, 탈퇴 사유에 해당될 경우</p><p>5.인증회원의 사망, 구속 등으로 신원확인이나 전자거래가 불가능한 경우</p><p>6.인증서비스 가입 시 본인확인기관에서 전달받은 연계정보(CI)가 국적, 성별 등의 변경으로 더이상 유효하지 않음이 확인된 경우</p><p>7.회사가 인증서비스와 관련된 보안절차나 인증회원의 전자서명생성정보 유출과 같은 보안상의 이유로 기 발급된 인증서의 이용제한이 필요한 경우</p><p>8.전시, 사변, 천재지변 또는 이에 준하는 비상사태가 발생하거나 발생할 우려가 있는 경우</p><p>9.인증회원의 인증서가 부정하게 사용된 사실을 회사가 인지한 경우 등 인증회원이 본 약관 및 운영정책을 포함한 회사의 서비스 이용 정책을 위반하거나 위반할 우려가 있다고 회사가 판단하는 경우</p><p>10.기타 인증서비스의 안전성과 신뢰성을 저해할 우려가 있는 경우</p><p>⑦회사는 인증서를 사용하는 인증회원과 이용기관 상호간 거래에 대하여 어떠한 책임도 부담하지 않으며, 회사는 인증회원과 이용기관의 귀책사유로 인하여 발생한 손해에 대하여 회사의 귀책사유가 없는 경우 책임을 부담하지 않습니다.</p><p>⑧본 조에서 정하고 있는 내용 외에 인증서비스와 관련된 상세한 사항은 전자서명법 등을 포함한 관련법령 및 회사가 별도로 정한 전자서명인증업무준칙에 따르며, 회사는 인증서비스 공지사항 및 고객센터 도움말 페이지 등을 통하여 회원에게 안내합니다.</p><p>&nbsp;</p><p>&nbsp;</p><p>제5장 계약해지 및 이용제한</p><p><strong>&nbsp;</strong></p><p><strong>제 14조 (개인정보의 보호)</strong></p><p>회원의 개인정보의 안전한 처리는 회사에게 있어 가장 중요한 일 중 하나입니다. 회원의 개인정보는 서비스의 원활한 제공을 위하여 회원이 동의한 목적과 범위 내에서만 이용됩니다. 법령에 의하거나 회원이 별도로 동의하지 아니하는 한 회사가 회원의 개인정보를 제3자에게 제공하는 일은 결코 없으므로 안심하시기바랍니다..</p><p>&nbsp;</p><p><strong>제15조 (회원에 대한 통지 및 공지)</strong></p><p>회사는 회원과의 의견 교환을 소중하게 생각합니다. 회원은 언제든지 고객센터에 방문하여 의견을 개진할 수 있습니다. 서비스 이용자 전체에 대한 공지는 칠(7)일 이상 서비스 공지사항란에 게시함으로써 효력이 발생합니다.회원에 중대한 영향을 미치는 사항의 경우에는 등록된 이메일 주소로 개별적으로 알려 드리겠습니다.</p><p>&nbsp;</p><p><strong>제16조 (이용계약 해지)</strong></p><p>①회원이 0918 이용을 더 이상 원치 않는 때에는 언제든지 서비스 내 제공되는 메뉴를 이용하여 이용계약의 해지 신청을 할 수 있으며, 회사는 법령이 정하는 바에 따라 신속히 처리하겠습니다.</p><p>&nbsp;</p><p>&nbsp;</p><p><strong>제 17 조 (손해배상)</strong></p><p>①회사는 법령상 허용되는 한도 내에서 서비스와 관련하여 본 약관에 명시되지 않은 어떠한 구체적인 사항에 대한 약정이나 보증을 하지 않습니다. 또한, 회사는 CP(Contents Provider)가 제공하거나 회원이 작성하는 등의 방법으로 서비스에 게재된 정보, 자료, 사실의 신뢰도, 정확성 등에 대해서는 보증을 하지 않으며, 회사의 과실 없이 발생된 여러분의 손해에 대하여는 책임을 부담하지 아니합니다.</p><p>②회사는 회사의 과실로 인하여 여러분이 손해를 입게 될 경우 본 약관 및 관련 법령에 따라 여러분의 손해를 배상하겠습니다. 다만 회사는 회사의 과실 없이 발생된 아래와 같은 손해에 대해서는 책임을 부담하지 않습니다. 또한 회사는 법률상 허용되는 한도 내에서 간접 손해, 특별 손해, 결과적 손해, 징계적 손해, 및 징벌적 손해에 대한 책임을 부담하지 않습니다.</p><p>1.천재지변 또는 이에 준하는 불가항력의 상태에서 발생한 손해</p><p>2.회원의 귀책사유로 서비스 이용에 장애가 발생한 경우</p><p>3.서비스에 접속 또는 이용과정에서 발생하는 개인적인 손해</p><p>4.제3자가 불법적으로 회사의 서버에 접속하거나 서버를 이용함으로써 발생하는 손해</p><p>5.제3자가 회사 서버에 대한 전송 또는 회사 서버로부터의 전송을 방해함으로써 발생하는 손해</p><p>6.제3자가 악성 프로그램을 전송 또는 유포함으로써 발생하는 손해</p><p>7.전송된 데이터의 생략, 누락, 파괴 등으로 발생한 손해, 명예훼손 등 제3자가 서비스를 이용하는 과정에서 발생된 손해</p><p>8.기타 회사의 고의 또는 과실이 없는 사유로 인해 발생한 손해</p><p>&nbsp;</p><p><strong>제 18 조 (분쟁의 해결)</strong></p><p>본 약관 또는 서비스는 대한민국법령에 의하여 규정되고 이행됩니다. 서비스 이용과 관련하여 회사와 회원 간에 분쟁이 발생하면 이의 해결을 위해 성실히 협의할 것입니다. 그럼에도 불구하고 해결되지 않으면 민사소송법상의 관할법원에 소를 제기할 수 있습니다.</p><p>&nbsp;</p><p><em>공고일자 : 2023년 8월 31일</em></p><p><em>시행일자 : 2023년 8월 31일</em></p>', 'N', '2099-10-24', '2023-10-17', 'ver 1.0', 'TERMS', 'Y', NULL, NULL, NULL, NULL);
/*!40000 ALTER TABLE `board` ENABLE KEYS */;

-- 테이블 ATTENDANCE.code 구조 내보내기
CREATE TABLE IF NOT EXISTS `code` (
  `code_id` bigint NOT NULL AUTO_INCREMENT,
  `code` varchar(255) DEFAULT NULL,
  `code_group` varchar(255) DEFAULT NULL,
  `code_order` int DEFAULT NULL,
  `key1` varchar(255) DEFAULT NULL,
  `key2` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `use_yn` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`code_id`),
  UNIQUE KEY `UK_de1dtuynd2biba9l1snao3bt5` (`code`),
  UNIQUE KEY `UK_fn5v09eyibqhg5blb3jol8sao` (`code_order`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- 테이블 데이터 ATTENDANCE.code:~0 rows (대략적) 내보내기
DELETE FROM `code`;
/*!40000 ALTER TABLE `code` DISABLE KEYS */;
/*!40000 ALTER TABLE `code` ENABLE KEYS */;

-- 테이블 ATTENDANCE.company 구조 내보내기
CREATE TABLE IF NOT EXISTS `company` (
  `company_id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`company_id`),
  UNIQUE KEY `UK_niu8sfil2gxywcru9ah3r4ec5` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- 테이블 데이터 ATTENDANCE.company:~0 rows (대략적) 내보내기
DELETE FROM `company`;
/*!40000 ALTER TABLE `company` DISABLE KEYS */;
INSERT INTO `company` (`company_id`, `name`) VALUES
	(1, 'GS ITM'),
	(2, 'ㅅㄷㄴㅅ');
/*!40000 ALTER TABLE `company` ENABLE KEYS */;

-- 테이블 ATTENDANCE.company_location 구조 내보내기
CREATE TABLE IF NOT EXISTS `company_location` (
  `company_location_id` bigint NOT NULL AUTO_INCREMENT,
  `company_id` bigint DEFAULT NULL,
  `location_id` bigint DEFAULT NULL,
  PRIMARY KEY (`company_location_id`),
  KEY `FKnh3ngnae5dpu79so8shcmmyk8` (`company_id`),
  KEY `FKngrh5kvhfp1sdnho8m1bbayik` (`location_id`),
  CONSTRAINT `FKngrh5kvhfp1sdnho8m1bbayik` FOREIGN KEY (`location_id`) REFERENCES `location` (`location_id`),
  CONSTRAINT `FKnh3ngnae5dpu79so8shcmmyk8` FOREIGN KEY (`company_id`) REFERENCES `company` (`company_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- 테이블 데이터 ATTENDANCE.company_location:~0 rows (대략적) 내보내기
DELETE FROM `company_location`;
/*!40000 ALTER TABLE `company_location` DISABLE KEYS */;
INSERT INTO `company_location` (`company_location_id`, `company_id`, `location_id`) VALUES
	(1, 1, 1);
/*!40000 ALTER TABLE `company_location` ENABLE KEYS */;

-- 테이블 ATTENDANCE.location 구조 내보내기
CREATE TABLE IF NOT EXISTS `location` (
  `location_id` bigint NOT NULL AUTO_INCREMENT,
  `latitude` float NOT NULL,
  `longitude` float NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`location_id`),
  UNIQUE KEY `UK_sahixf1v7f7xns19cbg12d946` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- 테이블 데이터 ATTENDANCE.location:~0 rows (대략적) 내보내기
DELETE FROM `location`;
/*!40000 ALTER TABLE `location` DISABLE KEYS */;
INSERT INTO `location` (`location_id`, `latitude`, `longitude`, `name`) VALUES
	(1, 37.502, 127.037, '역삼 GS 타워');
/*!40000 ALTER TABLE `location` ENABLE KEYS */;

-- 테이블 ATTENDANCE.member 구조 내보내기
CREATE TABLE IF NOT EXISTS `member` (
  `member_id` bigint NOT NULL AUTO_INCREMENT,
  `contact` varchar(50) DEFAULT NULL,
  `delete_yn` varchar(1) DEFAULT NULL,
  `use_yn` varchar(1) DEFAULT NULL,
  `email` varchar(100) NOT NULL,
  `insert_date` datetime(6) DEFAULT NULL,
  `login_fail_cnt` int DEFAULT NULL,
  `name` varchar(50) NOT NULL,
  `password` varchar(256) NOT NULL,
  `password_expiry_date` datetime(6) DEFAULT NULL,
  `push_agree_yn` varchar(1) DEFAULT NULL,
  `update_date` datetime(6) DEFAULT NULL,
  `company_id` bigint DEFAULT NULL,
  `team_id` bigint DEFAULT NULL,
  PRIMARY KEY (`member_id`),
  UNIQUE KEY `UK_mbmcqelty0fbrvxp1q58dn57t` (`email`),
  KEY `FKax2gealrg44mnq3ibas3q9de6` (`company_id`),
  KEY `FKcjte2jn9pvo9ud2hyfgwcja0k` (`team_id`),
  CONSTRAINT `FKax2gealrg44mnq3ibas3q9de6` FOREIGN KEY (`company_id`) REFERENCES `company` (`company_id`),
  CONSTRAINT `FKcjte2jn9pvo9ud2hyfgwcja0k` FOREIGN KEY (`team_id`) REFERENCES `team` (`team_id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- 테이블 데이터 ATTENDANCE.member:~8 rows (대략적) 내보내기
DELETE FROM `member`;
/*!40000 ALTER TABLE `member` DISABLE KEYS */;
INSERT INTO `member` (`member_id`, `contact`, `delete_yn`, `use_yn`, `email`, `insert_date`, `login_fail_cnt`, `name`, `password`, `password_expiry_date`, `push_agree_yn`, `update_date`, `company_id`, `team_id`) VALUES
	(1, '1', 'N', NULL, 'test@test.com', '2023-09-26 14:54:09.000000', 0, '장대현', 'test', '2023-09-26 14:54:17.000000', 'Y', '2023-09-26 14:54:20.000000', 1, 1),
	(5, '2', 'N', NULL, 'test1', '2023-09-26 14:54:38.000000', 0, '박다솜', 'test1', '2023-09-26 14:54:43.000000', 'Y', '2023-09-26 14:54:44.000000', 1, 1),
	(12, '1', 'N', NULL, 'test2', '2023-09-26 14:54:09.000000', 0, '장대현', 'test', '2023-09-26 14:54:17.000000', 'Y', '2023-09-26 14:54:20.000000', 1, 1),
	(13, '1', 'N', NULL, 'test3', '2023-09-26 14:54:09.000000', 0, '장대현', 'test', '2023-09-26 14:54:17.000000', 'Y', '2023-09-26 14:54:20.000000', 1, 1),
	(14, '1', 'N', NULL, 'test4', '2023-09-26 14:54:09.000000', 0, '장대현', 'test', '2023-09-26 14:54:17.000000', 'Y', '2023-09-26 14:54:20.000000', 1, 1),
	(15, '1', 'N', NULL, 'test5', '2023-09-26 14:54:09.000000', 0, '장대현', 'test', '2023-09-26 14:54:17.000000', 'Y', '2023-09-26 14:54:20.000000', 1, 1),
	(16, '1', 'N', NULL, 'test6', '2023-09-26 14:54:09.000000', 0, '장대현', 'test', '2023-09-26 14:54:17.000000', 'Y', '2023-09-26 14:54:20.000000', 1, 1),
	(17, '010-9323-1494', 'N', NULL, 'it1713@gsitm.com', '2023-10-13 10:59:13.506573', 0, '장대현', '$2a$10$Q.AzT8LTISEKMRaQz7kRiuSfFelHTtWwNArHuMpsWf8dk0ZoZq8La', '2024-01-11 10:59:13.506573', 'Y', NULL, 1, 1);
/*!40000 ALTER TABLE `member` ENABLE KEYS */;

-- 테이블 ATTENDANCE.member_role 구조 내보내기
CREATE TABLE IF NOT EXISTS `member_role` (
  `member_role_id` bigint NOT NULL AUTO_INCREMENT,
  `role` varchar(255) DEFAULT NULL,
  `member_id` bigint DEFAULT NULL,
  PRIMARY KEY (`member_role_id`),
  KEY `FK34g7epqlcxqloewku3aoqhhmg` (`member_id`),
  CONSTRAINT `FK34g7epqlcxqloewku3aoqhhmg` FOREIGN KEY (`member_id`) REFERENCES `member` (`member_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- 테이블 데이터 ATTENDANCE.member_role:~0 rows (대략적) 내보내기
DELETE FROM `member_role`;
/*!40000 ALTER TABLE `member_role` DISABLE KEYS */;
INSERT INTO `member_role` (`member_role_id`, `role`, `member_id`) VALUES
	(1, 'ROLE_ADMIN', 17),
	(2, 'ROLE_MANAGER', 17);
/*!40000 ALTER TABLE `member_role` ENABLE KEYS */;

-- 테이블 ATTENDANCE.refresh_token 구조 내보내기
CREATE TABLE IF NOT EXISTS `refresh_token` (
  `email` varchar(255) NOT NULL,
  `expiry_date` datetime(6) DEFAULT NULL,
  `insert_date` datetime(6) DEFAULT NULL,
  `token` varchar(255) DEFAULT NULL,
  `update_date` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- 테이블 데이터 ATTENDANCE.refresh_token:~1 rows (대략적) 내보내기
DELETE FROM `refresh_token`;
/*!40000 ALTER TABLE `refresh_token` DISABLE KEYS */;
INSERT INTO `refresh_token` (`email`, `expiry_date`, `insert_date`, `token`, `update_date`) VALUES
	('it1713@gsitm.com', '2024-01-17 15:52:11.226000', '2023-10-19 15:52:11.289651', 'eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJpdDE3MTNAZ3NpdG0uY29tIiwiYXV0aCI6IlJPTEVfTUVNQkVSIiwiaWQiOjE3LCJleHAiOjE3MDU0NzQzMzF9.99ugoXSuGVwI9EahlIBOT3swfp_pUK_9iwuWh1RL9fwB_ZQcn0qXdJzTX-oDa2gS8ysyWEZwKk3dKhrRV9BW5w', NULL);
/*!40000 ALTER TABLE `refresh_token` ENABLE KEYS */;

-- 테이블 ATTENDANCE.team 구조 내보내기
CREATE TABLE IF NOT EXISTS `team` (
  `team_id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  `company_id` bigint DEFAULT NULL,
  PRIMARY KEY (`team_id`),
  KEY `FKq7becd703i1w0ry1jxy15qxid` (`company_id`),
  CONSTRAINT `FKq7becd703i1w0ry1jxy15qxid` FOREIGN KEY (`company_id`) REFERENCES `company` (`company_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- 테이블 데이터 ATTENDANCE.team:~2 rows (대략적) 내보내기
DELETE FROM `team`;
/*!40000 ALTER TABLE `team` DISABLE KEYS */;
INSERT INTO `team` (`team_id`, `name`, `company_id`) VALUES
	(1, '모바일팀', 1),
	(2, 'TEST1', 1),
	(3, 'test2', 1);
/*!40000 ALTER TABLE `team` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
