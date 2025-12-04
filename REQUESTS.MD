# BankWallet SOAP API - All Endpoints

Base URL: `http://localhost:8080/ws`
Namespace: `http://uws.provider.com/`

---

## USER SERVICE ENDPOINTS

### 1. Register User

```bash
curl --location 'http://localhost:8080/ws' \
--header 'Content-Type: text/xml; charset=utf-8' \
--header 'SOAPAction: http://uws.provider.com/RegisterUser' \
--data '<?xml version="1.0" encoding="UTF-8"?>
<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/"
    xmlns:uws="http://uws.provider.com/">
    <soapenv:Header/>
    <soapenv:Body>
        <uws:RegisterUserRequest>
            <uws:username>testuser</uws:username>
            <uws:password>testpassword123</uws:password>
            <uws:phoneNumber>+998901234567</uws:phoneNumber>
        </uws:RegisterUserRequest>
    </soapenv:Body>
</soapenv:Envelope>'
```

---

### 2. Change Password

```bash
curl --location 'http://localhost:8080/ws' \
--header 'Content-Type: text/xml; charset=utf-8' \
--header 'SOAPAction: http://uws.provider.com/ChangePassword' \
--data '<?xml version="1.0" encoding="UTF-8"?>
<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/"
    xmlns:uws="http://uws.provider.com/">
    <soapenv:Header/>
    <soapenv:Body>
        <uws:ChangePasswordRequest>
            <uws:username>testuser</uws:username>
            <uws:password>testpassword123</uws:password>
            <uws:newPassword>newpassword456</uws:newPassword>
        </uws:ChangePasswordRequest>
    </soapenv:Body>
</soapenv:Envelope>'
```

---

## TRANSACTION SERVICE ENDPOINTS

### 3. Perform Transaction

```bash
curl --location 'http://localhost:8080/ws' \
--header 'Content-Type: text/xml; charset=utf-8' \
--header 'SOAPAction: http://uws.provider.com/PerformTransaction' \
--data '<?xml version="1.0" encoding="UTF-8"?>
<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/"
    xmlns:uws="http://uws.provider.com/">
    <soapenv:Header/>
    <soapenv:Body>
        <uws:PerformTransactionRequest>
            <uws:username>testuser</uws:username>
            <uws:password>testpassword123</uws:password>
            <uws:amount>50000</uws:amount>
            <uws:parameters>
                <uws:paramKey>phoneNumber</uws:paramKey>
                <uws:paramValue>+998901234567</uws:paramValue>
            </uws:parameters>
            <uws:parameters>
                <uws:paramKey>walletNumber</uws:paramKey>
                <uws:paramValue>12345678901234567</uws:paramValue>
            </uws:parameters>
            <uws:serviceId>1</uws:serviceId>
            <uws:transactionId>12345</uws:transactionId>
        </uws:PerformTransactionRequest>
    </soapenv:Body>
</soapenv:Envelope>'
```

---

### 4. Check Transaction

```bash
curl --location 'http://localhost:8080/ws' \
--header 'Content-Type: text/xml; charset=utf-8' \
--header 'SOAPAction: http://uws.provider.com/CheckTransaction' \
--data '<?xml version="1.0" encoding="UTF-8"?>
<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/"
    xmlns:uws="http://uws.provider.com/">
    <soapenv:Header/>
    <soapenv:Body>
        <uws:CheckTransactionRequest>
            <uws:username>testuser</uws:username>
            <uws:password>testpassword123</uws:password>
            <uws:serviceId>1</uws:serviceId>
            <uws:transactionId>12345</uws:transactionId>
        </uws:CheckTransactionRequest>
    </soapenv:Body>
</soapenv:Envelope>'
```

---

### 5. Cancel Transaction

```bash
curl --location 'http://localhost:8080/ws' \
--header 'Content-Type: text/xml; charset=utf-8' \
--header 'SOAPAction: http://uws.provider.com/CancelTransaction' \
--data '<?xml version="1.0" encoding="UTF-8"?>
<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/"
    xmlns:uws="http://uws.provider.com/">
    <soapenv:Header/>
    <soapenv:Body>
        <uws:CancelTransactionRequest>
            <uws:username>testuser</uws:username>
            <uws:password>testpassword123</uws:password>
            <uws:serviceId>1</uws:serviceId>
            <uws:transactionId>12345</uws:transactionId>
        </uws:CancelTransactionRequest>
    </soapenv:Body>
</soapenv:Envelope>'
```

---

### 6. Get Statement

```bash
curl --location 'http://localhost:8080/ws' \
--header 'Content-Type: text/xml; charset=utf-8' \
--header 'SOAPAction: http://uws.provider.com/GetStatement' \
--data '<?xml version="1.0" encoding="UTF-8"?>
<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/"
    xmlns:uws="http://uws.provider.com/">
    <soapenv:Header/>
    <soapenv:Body>
        <uws:GetStatementRequest>
            <uws:username>testuser</uws:username>
            <uws:password>testpassword123</uws:password>
            <uws:serviceId>1</uws:serviceId>
            <uws:dateFrom>2025-12-01T00:00:00</uws:dateFrom>
            <uws:dateTo>2025-12-04T23:59:59</uws:dateTo>
        </uws:GetStatementRequest>
    </soapenv:Body>
</soapenv:Envelope>'
```

---

### 7. Get Information

```bash
curl --location 'http://localhost:8080/ws' \
--header 'Content-Type: text/xml; charset=utf-8' \
--header 'SOAPAction: http://uws.provider.com/GetInformation' \
--data '<?xml version="1.0" encoding="UTF-8"?>
<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/"
    xmlns:uws="http://uws.provider.com/">
    <soapenv:Header/>
    <soapenv:Body>
        <uws:GetInformationRequest>
            <uws:username>testuser</uws:username>
            <uws:password>testpassword123</uws:password>
            <uws:parameters>
                <uws:paramKey>account</uws:paramKey>
                <uws:paramValue>12345678901234567</uws:paramValue>
            </uws:parameters>
            <uws:serviceId>1</uws:serviceId>
        </uws:GetInformationRequest>
    </soapenv:Body>
</soapenv:Envelope>'
```

---

## Notes

- All endpoints use the same base URL: `http://localhost:8080/ws`
- Server port configured in application.yml: `8080`
- All requests require username and password authentication
- SOAPAction header matches the operation name in the WSDL
- All XML elements are prefixed with `uws:` namespace
- Replace test values with actual data when making requests
- Date format: ISO 8601 (YYYY-MM-DDTHH:MM:SS)