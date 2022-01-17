:: go and build discovery-service-server
cd ../
cd discovery-service-server
call mvn package
cd ../

:: go and build product service
cd product-service
call mvn package
cd ../

:: go and build customer service
cd customer-service
call mvn package
cd ../

:: go and build shopping cart service
cd shoppingcart-service
call mvn package
cd ../

:: go and build order service
cd purchase-order-service
call mvn package
cd ../

:: go and build gateway service
cd gateway
call mvn package
cd ../